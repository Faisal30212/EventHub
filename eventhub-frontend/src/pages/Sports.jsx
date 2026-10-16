import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import API_BASE_URL from "import API_BASE_URL from "../api/Api.js";.js";
import sportsImage from "../assets/images/sports.jpg";
import "./Sports.css";

function Sports() {
  const navigate = useNavigate();

  const [events, setEvents] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    fetch(`${API_BASE_URL}/events`)
      .then((response) => response.json())
      .then((data) => {
        const sportsEvents = data.filter(
          (event) => event.category === "SPORTS",
        );

        setEvents(sportsEvents);
        setLoading(false);
      })
      .catch((error) => {
        console.error("Error fetching sports events:", error);
        setLoading(false);
      });
  }, []);

  return (
    <div className="sports-page">
      <div className="container">
        <h1 className="sports-title">Sports Events ⚽</h1>

        <p className="sports-subtitle">
          Experience the excitement, competition and energy of live sports
        </p>

        {loading && <p className="sports-empty">Loading sports events...</p>}

        {!loading && events.length === 0 && (
          <p className="sports-empty">No sports events available.</p>
        )}

        <div className="sports-grid">
          {events.map((event) => (
            <div className="sports-card" key={event.eventId}>
              <div className="sports-image-container">
                <img
                  src={sportsImage}
                  className="sports-image"
                  alt={event.eventName}
                />

                <span className="sports-badge">LIVE ⚡</span>
              </div>

              <div className="sports-card-body">
                <h2>{event.eventName}</h2>

                <p className="sports-description">{event.description}</p>

                <div className="sports-details">
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

                <div className="sports-bottom">
                  <span>Starting from</span>

                  <strong>₹{event.basePrice}</strong>
                </div>

                <button
                  className="sports-button"
                  onClick={() => navigate(`/sports/${event.eventId}`)}
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

export default Sports;
