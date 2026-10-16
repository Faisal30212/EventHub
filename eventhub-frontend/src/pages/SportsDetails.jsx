import { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import API_BASE_URL from "import API_BASE_URL from "../api/Api.js";.js";
import sportsImage from "../assets/images/sports.jpg";
import "./SportsDetails.css";

function SportsDetails() {
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
        console.error("Error fetching sports event:", error);
        setLoading(false);
      });
  }, [id]);

  if (loading) {
    return (
      <div className="sports-details-loading">Loading sports event...</div>
    );
  }

  if (!event) {
    return (
      <div className="sports-details-loading">Sports event not found.</div>
    );
  }

  return (
    <div className="sports-details-page">
      <div className="sports-details-card">
        <img
          src={sportsImage}
          alt={event.eventName}
          className="sports-details-image"
        />

        <div className="sports-details-content">
          <span className="sports-details-badge">LIVE ⚡</span>

          <h1>{event.eventName}</h1>

          <p className="sports-details-description">{event.description}</p>

          <div className="sports-details-info">
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

          <div className="sports-details-price">
            <span>Starting from</span>

            <strong>₹{event.basePrice}</strong>
          </div>

          {/* Book Now */}

          <button
            className="sports-book-button"
            onClick={() => navigate(`/bookings/${event.eventId}`)}
          >
            🎟️ Book Now
          </button>

          {/* Back */}

          <button
            className="sports-back-button"
            onClick={() => navigate("/sports")}
          >
            ← Back to Sports Events
          </button>
        </div>
      </div>
    </div>
  );
}

export default SportsDetails;
