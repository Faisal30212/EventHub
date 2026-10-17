import { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import "./BirthdayDetails.css";

const API_BASE_URL = "https://eventhub-production-4dcd.up.railway.app";

function BirthdayDetails() {
  const { id } = useParams();
  const navigate = useNavigate();

  const [event, setEvent] = useState(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const loadEvent = async () => {
      try {
        const response = await fetch(`${API_BASE_URL}/events/${id}`);

        if (!response.ok) {
          throw new Error(`Event API returned ${response.status}`);
        }

        const data = await response.json();

        setEvent(data);
      } catch (error) {
        console.error("Error fetching birthday event:", error);
        setEvent(null);
      } finally {
        setLoading(false);
      }
    };

    loadEvent();
  }, [id]);

  if (loading) {
    return (
      <div className="birthday-details-loading">Loading birthday event...</div>
    );
  }

  if (!event) {
    return (
      <div className="birthday-details-loading">Birthday event not found.</div>
    );
  }

  return (
    <div className="birthday-details-page">
      <div className="birthday-details-card">
        <img
          src="/src/assets/images/birthday.jpg"
          alt={event.eventName}
          className="birthday-details-image"
        />

        <div className="birthday-details-content">
          <h1>{event.eventName} 🎂</h1>

          <p className="birthday-details-description">{event.description}</p>

          <div className="birthday-details-info">
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
              <span>{event.eventDate || "Not specified"}</span>
            </p>

            <p>
              <strong>⏰ Time</strong>
              <span>{event.eventTime || "Not specified"}</span>
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

          <div className="birthday-details-price">
            <span>Starting from</span>

            <strong>
              ₹{Number(event.basePrice || 0).toLocaleString("en-IN")}
            </strong>
          </div>

          <button
            type="button"
            className="birthday-book-button"
            onClick={() => navigate(`/bookings/${event.eventId}`)}
          >
            🎟️ Book Now
          </button>

          <button
            type="button"
            className="birthday-back-button"
            onClick={() => navigate("/birthday")}
          >
            ← Back to Birthday Events
          </button>
        </div>
      </div>
    </div>
  );
}

export default BirthdayDetails;
