import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import API_BASE_URL from "../api/api";
import concertImage from "../assets/images/concert.jpg";
import "./Concert.css";

function Concert() {
  const navigate = useNavigate();

  const [events, setEvents] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    fetch(`${API_BASE_URL}/events`)
      .then((response) => response.json())
      .then((data) => {
        const concertEvents = data.filter(
          (event) => event.category === "CONCERT",
        );

        setEvents(concertEvents);
        setLoading(false);
      })
      .catch((error) => {
        console.error("Error fetching events:", error);
        setLoading(false);
      });
  }, []);

  return (
    <div className="concert-page">
      <div className="container">
        <h1 className="concert-title">Live Concerts 🎵</h1>

        <p className="concert-subtitle">
          Experience unforgettable music, lights and live performances
        </p>

        {loading && <p className="concert-empty">Loading concerts...</p>}

        {!loading && events.length === 0 && (
          <p className="concert-empty">No concerts available.</p>
        )}

        <div className="concert-grid">
          {events.map((event) => (
            <div className="concert-card" key={event.eventId}>
              <div className="concert-image-container">
                <img
                  src={concertImage}
                  className="concert-image"
                  alt={event.eventName}
                />

                <span className="concert-badge">LIVE 🎵</span>
              </div>

              <div className="concert-card-body">
                <h2>{event.eventName}</h2>

                <p className="concert-description">{event.description}</p>

                <div className="concert-details">
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

                <div className="concert-bottom">
                  <span>Starting from</span>

                  <strong>₹{event.basePrice}</strong>
                </div>

                <button
                  className="concert-button"
                  onClick={() => navigate(`/concert/${event.eventId}`)}
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

export default Concert;
