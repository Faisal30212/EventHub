import { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import API_BASE_URL from "import API_BASE_URL from "../api/Api.js";.js";
import concertImage from "../assets/images/concert.jpg";
import "./ConcertDetails.css";

function ConcertDetails() {
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
    return <div className="concert-details-loading">Loading concert...</div>;
  }

  if (!event) {
    return <div className="concert-details-loading">Concert not found.</div>;
  }

  return (
    <div className="concert-details-page">
      <div className="concert-details-card">
        <img
          src={concertImage}
          alt={event.eventName}
          className="concert-details-image"
        />

        <div className="concert-details-content">
          <span className="concert-details-badge">LIVE 🎵</span>

          <h1>{event.eventName}</h1>

          <p className="concert-details-description">{event.description}</p>

          <div className="concert-details-info">
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

          <div className="concert-details-price">
            <span>Starting from</span>

            <strong>₹{event.basePrice}</strong>
          </div>

          {/* Book Now */}

          <button
            className="concert-book-button"
            onClick={() => navigate(`/bookings/${event.eventId}`)}
          >
            🎟️ Book Now
          </button>

          {/* Back */}

          <button
            className="concert-back-button"
            onClick={() => navigate("/concert")}
          >
            ← Back to Concerts
          </button>
        </div>
      </div>
    </div>
  );
}

export default ConcertDetails;
