import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import API_BASE_URL from "../api/Api.js";
import "./Birthday.css";

function Birthday() {
  const navigate = useNavigate();

  const [events, setEvents] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    fetch(`${API_BASE_URL}/events`)
      .then((response) => response.json())
      .then((data) => {
        const birthdayEvents = data.filter(
          (event) => event.category === "BIRTHDAY",
        );

        setEvents(birthdayEvents);
        setLoading(false);
      })
      .catch((error) => {
        console.error("Error fetching events:", error);
        setLoading(false);
      });
  }, []);

  return (
    <div className="birthday-page">
      <div className="container">
        <h1 className="birthday-title">Birthday Events 🎂</h1>

        <p className="birthday-subtitle">
          Celebrate your special day with unforgettable memories ✨
        </p>

        {loading && (
          <p className="birthday-empty">Loading birthday events...</p>
        )}

        {!loading && events.length === 0 && (
          <p className="birthday-empty">No birthday events available.</p>
        )}

        <div className="birthday-grid">
          {events.map((event) => (
            <div className="birthday-card" key={event.eventId}>
              <img
                src={birthdayImage}
                className="birthday-image"
                alt={event.eventName}
              />

              <div className="birthday-card-body">
                <h2>{event.eventName}</h2>

                <p className="birthday-description">{event.description}</p>

                <div className="birthday-details">
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

                <div className="birthday-bottom">
                  <span>Starting from</span>

                  <strong>₹{event.basePrice}</strong>
                </div>

                <button
                  className="birthday-button"
                  onClick={() => navigate(`/birthday/${event.eventId}`)}
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

export default Birthday;
