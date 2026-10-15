import { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import API_BASE_URL from "../api/api";
import collegeFestImage from "../assets/images/collegefest.jpg";
import "./CollegeFestDetails.css";

function CollegeFestDetails() {
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
        console.error("Error fetching college fest event:", error);
        setLoading(false);
      });
  }, [id]);

  if (loading) {
    return (
      <div className="collegefest-details-loading">Loading college fest...</div>
    );
  }

  if (!event) {
    return (
      <div className="collegefest-details-loading">College fest not found.</div>
    );
  }

  return (
    <div className="collegefest-details-page">
      <div className="collegefest-details-card">
        <img
          src={collegeFestImage}
          alt={event.eventName}
          className="collegefest-details-image"
        />

        <div className="collegefest-details-content">
          <span className="collegefest-details-badge">FEST '26 🎊</span>

          <h1>{event.eventName}</h1>

          <p className="collegefest-details-description">{event.description}</p>

          <div className="collegefest-details-info">
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

          <div className="collegefest-details-price">
            <span>Starting from</span>

            <strong>₹{event.basePrice}</strong>
          </div>

          {/* Book Now */}

          <button
            className="collegefest-book-button"
            onClick={() => navigate(`/bookings/${event.eventId}`)}
          >
            🎟️ Book Now
          </button>

          {/* Back */}

          <button
            className="collegefest-back-button"
            onClick={() => navigate("/collegefest")}
          >
            ← Back to College Fest
          </button>
        </div>
      </div>
    </div>
  );
}

export default CollegeFestDetails;
