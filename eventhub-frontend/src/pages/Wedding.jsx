import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import API_BASE_URL from "../api/api";
import weddingImage from "../assets/images/wedding.jpg";
import "./Wedding.css";

function Wedding() {
  const navigate = useNavigate();
  const [events, setEvents] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    fetch(`${API_BASE_URL}/events`)
      .then((response) => response.json())
      .then((data) => {
        const weddingEvents = data.filter(
          (event) => event.category === "WEDDING",
        );

        setEvents(weddingEvents);
        setLoading(false);
      })
      .catch((error) => {
        console.error("Error fetching events:", error);
        setLoading(false);
      });
  }, []);

  return (
    <div className="wedding-page">
      <div className="container">
        <h1 className="wedding-title">Wedding Events 💍</h1>

        <p className="wedding-subtitle">
          Make your special day truly unforgettable ✨
        </p>

        {loading && <p className="wedding-empty">Loading wedding events...</p>}

        {!loading && events.length === 0 && (
          <p className="wedding-empty">No wedding events available.</p>
        )}

        <div className="wedding-grid">
          {events.map((event) => (
            <div className="wedding-card" key={event.eventId}>
              <img
                src={weddingImage}
                className="wedding-image"
                alt={event.eventName}
              />

              <div className="wedding-card-body">
                <h2>{event.eventName}</h2>

                <p className="wedding-description">{event.description}</p>

                <div className="wedding-details">
                  <p>
                    <strong>📍 Venue</strong>
                    <span>{event.venue?.venueName}</span>
                  </p>

                  <p>
                    <strong>🏙️ City</strong>
                    <span>{event.venue?.city}</span>
                  </p>

                  <p>
                    <strong>📅 Date</strong>
                    <span>{event.eventDate}</span>
                  </p>

                  <p>
                    <strong>⏰ Time</strong>
                    <span>{event.eventTime}</span>
                  </p>
                </div>

                <div className="wedding-bottom">
                  <span>Starting from</span>

                  <strong>₹{event.basePrice}</strong>
                </div>

                <button
                  className="wedding-button"
                  onClick={() => navigate(`/wedding/${event.eventId}`)}
                >
                  View Details
                </button>
              </div>
            </div>
          ))}
        </div>
      </div>
    </div>
  );
}

export default Wedding;
