import { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import API_BASE_URL from "import API_BASE_URL from "../api/Api.js";.js";
import conferenceImage from "../assets/images/conference.jpg";
import "./ConferenceDetails.css";

function ConferenceDetails() {
  const { id } = useParams();
  const navigate = useNavigate();

  const [event, setEvent] = useState(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    fetch(`${API_BASE_URL}/events/${id}`)
      .then((response) => response.json())

      .then((data) => {
        setEvent(data);
        setLoading(false);
      })

      .catch((error) => {
        console.error("Error fetching event:", error);
        setLoading(false);
      });
  }, [id]);

  if (loading) {
    return (
      <div className="conference-details-loading">Loading conference...</div>
    );
  }

  if (!event) {
    return (
      <div className="conference-details-loading">Conference not found.</div>
    );
  }

  return (
    <div className="conference-details-page">
      <div className="conference-details-card">
        <img
          src={conferenceImage}
          alt={event.eventName}
          className="conference-details-image"
        />

        <div className="conference-details-content">
          <h1>{event.eventName} 🎓</h1>

          <p className="conference-details-description">{event.description}</p>

          <div className="conference-details-info">
            <p>
              <strong>📍 Venue</strong>

              <span>{event.venue?.venueName || "Not specified"}</span>
            </p>

            <p>
              <strong>🏙️ City</strong>

              <span>{event.venue?.city || "Not specified"}</span>
            </p>

            <p>
              <strong>📅 Date</strong>

              <span>{event.eventDate}</span>
            </p>

            <p>
              <strong>⏰ Time</strong>

              <span>{event.eventTime}</span>
            </p>

            <p>
              <strong>👥 Capacity</strong>

              <span>{event.capacity} people</span>
            </p>

            <p>
              <strong>⏱️ Duration</strong>

              <span>{event.durationHours} hours</span>
            </p>
          </div>

          <div className="conference-details-price">
            <span>Starting from</span>

            <strong>₹{event.basePrice}</strong>
          </div>

          {/* Book Now */}

          <button
            className="conference-book-button"
            onClick={() => navigate(`/bookings/${event.eventId}`)}
          >
            🎟️ Book Now
          </button>

          {/* Back */}

          <button
            className="conference-back-button"
            onClick={() => navigate("/conference")}
          >
            ← Back to Conferences
          </button>
        </div>
      </div>
    </div>
  );
}

export default ConferenceDetails;
