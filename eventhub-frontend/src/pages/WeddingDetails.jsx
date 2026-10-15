import { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import API_BASE_URL from "../api/api";
import weddingImage from "../assets/images/wedding.jpg";
import "./WeddingDetails.css";

function WeddingDetails() {
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
    return <div className="details-loading">Loading event...</div>;
  }

  if (!event) {
    return <div className="details-loading">Event not found.</div>;
  }

  return (
    <div className="wedding-details-page">
      <div className="wedding-details-card">
        <img
          src={weddingImage}
          alt={event.eventName}
          className="details-image"
        />

        <div className="details-content">
          <h1>{event.eventName} 💍</h1>

          <p className="details-description">{event.description}</p>

          <div className="details-info">
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

          <div className="details-price">
            <span>Starting from</span>

            <strong>₹{event.basePrice}</strong>
          </div>

          {/* Book Now Button */}

          <button
            className="book-now-button"
            onClick={() => navigate(`/bookings/${event.eventId}`)}
          >
            🎟️ Book Now
          </button>

          {/* Back Button */}

          <button className="back-button" onClick={() => navigate("/wedding")}>
            ← Back to Wedding Events
          </button>
        </div>
      </div>
    </div>
  );
}

export default WeddingDetails;
