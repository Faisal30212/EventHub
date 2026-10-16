import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import API_BASE_URL from "../api/Api.js";
import collegeFestImage from "../assets/images/collegefest.jpg";

import "./CollegeFest.css";

function CollegeFest() {
  const navigate = useNavigate();

  const [events, setEvents] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    fetch(`${API_BASE_URL}/events`)
      .then((response) => response.json())
      .then((data) => {
        const collegeFestEvents = data.filter(
          (event) => event.category === "COLLEGE_FEST",
        );

        setEvents(collegeFestEvents);
        setLoading(false);
      })
      .catch((error) => {
        console.error("Error fetching college fest events:", error);
        setLoading(false);
      });
  }, []);

  return (
    <div className="collegefest-page">
      <div className="container">
        <h1 className="collegefest-title">College Fest 🎉</h1>

        <p className="collegefest-subtitle">
          Celebrate talent, friendship, music and unforgettable college memories
        </p>

        {loading && (
          <p className="collegefest-empty">Loading college fest events...</p>
        )}

        {!loading && events.length === 0 && (
          <p className="collegefest-empty">No college fest events available.</p>
        )}

        <div className="collegefest-grid">
          {events.map((event) => (
            <div className="collegefest-card" key={event.eventId}>
              <div className="collegefest-image-container">
                <img
                  src={collegeFestImage}
                  className="collegefest-image"
                  alt={event.eventName}
                />

                <span className="collegefest-badge">FEST '26 🎊</span>
              </div>

              <div className="collegefest-card-body">
                <h2>{event.eventName}</h2>

                <p className="collegefest-description">{event.description}</p>

                <div className="collegefest-details">
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

                <div className="collegefest-bottom">
                  <span>Starting from</span>

                  <strong>₹{event.basePrice}</strong>
                </div>

                <button
                  className="collegefest-button"
                  onClick={() => navigate(`/collegefest/${event.eventId}`)}
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

export default CollegeFest;
