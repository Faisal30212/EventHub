import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";

import API_BASE_URL from "../api/Api.js";

import Venue1 from "../assets/images/Venue1.jpg";
import Venue2 from "../assets/images/Venue2.jpg";
import Venue3 from "../assets/images/Venue3.jpg";
import Venue4 from "../assets/images/Venue4.jpg";

import "./Venue.css";

function Venue() {
  const navigate = useNavigate();

  const [venues, setVenues] = useState([]);
  const [loading, setLoading] = useState(true);

  const venueImages = {
    3: Venue1,
    4: Venue2,
    5: Venue3,
    6: Venue4,
  };

  useEffect(() => {
    fetch(`${API_BASE_URL}/venues`)
      .then((response) => {
        if (!response.ok) {
          throw new Error("Failed to fetch venues");
        }

        return response.json();
      })
      .then((data) => {
        const ourVenues = data.filter(
          (venue) =>
            venue.venueId === 3 ||
            venue.venueId === 4 ||
            venue.venueId === 5 ||
            venue.venueId === 6,
        );

        setVenues(ourVenues);
        setLoading(false);
      })
      .catch((error) => {
        console.error("Error fetching venues:", error);
        setLoading(false);
      });
  }, []);

  if (loading) {
    return (
      <div className="venue-page">
        <div className="venue-loading">Loading venues...</div>
      </div>
    );
  }

  return (
    <div className="venue-page">
      <div className="venue-container">
        {/* Heading */}

        <div className="venue-heading">
          <span>EVENTHUB VENUES</span>

          <h1>Find Your Perfect Venue 🏢</h1>

          <p>
            Discover beautiful venues across Karnataka for weddings, birthdays,
            conferences and special events.
          </p>
        </div>

        {/* Venue Cards */}

        <div className="venue-grid">
          {venues.map((venue) => (
            <div className="venue-card" key={venue.venueId}>
              {/* Image */}

              <div className="venue-image-box">
                <img
                  src={venueImages[venue.venueId]}
                  alt={venue.venueName}
                  className="venue-image"
                />

                <span className="venue-status">{venue.availability}</span>
              </div>

              {/* Content */}

              <div className="venue-content">
                <h2>{venue.venueName}</h2>

                <p className="venue-location">📍 {venue.city}</p>

                <div className="venue-details">
                  <div>
                    <strong>👥 Capacity</strong>

                    <span>{venue.capacity} people</span>
                  </div>

                  <div>
                    <strong>💰 Price</strong>

                    <span>₹{venue.pricePerDay.toLocaleString()} / day</span>
                  </div>
                </div>

                <button
                  className="venue-button"
                  onClick={() => navigate(`/venues/${venue.venueId}`)}
                >
                  View Venue →
                </button>
              </div>
            </div>
          ))}
        </div>
      </div>
    </div>
  );
}

export default Venue;
