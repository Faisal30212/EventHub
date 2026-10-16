import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import API_BASE_URL from "import API_BASE_URL from "../api/Api.js";.js";
import conferenceImage from "../assets/images/conference.jpg";
import "./Conference.css";

function Conference() {
  const navigate = useNavigate();

  const [events, setEvents] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    fetch(`${API_BASE_URL}/events`)
      .then((response) => response.json())
      .then((data) => {
        const conferenceEvents = data.filter(
          (event) => event.category === "CONFERENCE",
        );

        setEvents(conferenceEvents);
        setLoading(false);
      })
      .catch((error) => {
        console.error("Error fetching events:", error);
        setLoading(false);
      });
  }, []);

  return (
    <div className="conference-page">
      <div className="container">
        <h1 className="conference-title">Conferences 🎓</h1>

        <p className="conference-subtitle">
          Connect, learn and grow with industry professionals
        </p>

        {loading && <p className="conference-empty">Loading conferences...</p>}

        {!loading && events.length === 0 && (
          <p className="conference-empty">No conferences available.</p>
        )}

        <div className="conference-grid">
          {events.map((event) => (
            <div className="conference-card" key={event.eventId}>
              <img
                src={conferenceImage}
                className="conference-image"
                alt={event.eventName}
              />

              <div className="conference-card-body">
                <h2>{event.eventName}</h2>

                <p className="conference-description">{event.description}</p>

                <div className="conference-details">
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

                <div className="conference-bottom">
                  <span>Starting from</span>

                  <strong>₹{event.basePrice}</strong>
                </div>

                <button
                  className="conference-button"
                  onClick={() => navigate(`/conference/${event.eventId}`)}
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

export default Conference;
