import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";

import API_BASE_URL from "../api/api";

import Venue1 from "../assets/images/Venue1.jpg";
import Venue2 from "../assets/images/Venue2.jpg";
import Venue3 from "../assets/images/Venue3.jpg";
import Venue4 from "../assets/images/Venue4.jpg";

import "./VenueDetails.css";

function VenueDetails() {
  const { id } = useParams();
  const navigate = useNavigate();

  const [venue, setVenue] = useState(null);
  const [loading, setLoading] = useState(true);

  const venueImages = {
    3: Venue1,
    4: Venue2,
    5: Venue3,
    6: Venue4,
  };

  useEffect(() => {
    fetch(`${API_BASE_URL}/venues/${id}`)
      .then((response) => {
        if (!response.ok) {
          throw new Error("Venue not found");
        }

        return response.json();
      })
      .then((data) => {
        setVenue(data);
        setLoading(false);
      })
      .catch((error) => {
        console.error("Error fetching venue:", error);
        setLoading(false);
      });
  }, [id]);

  if (loading) {
    return <div className="venue-details-loading">Loading venue...</div>;
  }

  if (!venue) {
    return (
      <div className="venue-details-loading">
        <p>Venue not found.</p>

        <button onClick={() => navigate("/venues")}>← Back to Venues</button>
      </div>
    );
  }

  return (
    <div className="venue-details-page">
      <div className="venue-details-card">
        {/* Image */}

        <div className="venue-details-image-box">
          <img
            src={venueImages[venue.venueId]}
            alt={venue.venueName}
            className="venue-details-image"
          />
        </div>

        {/* Details */}

        <div className="venue-details-content">
          <span className="venue-details-badge">EVENTHUB VENUE 🏢</span>

          <h1>{venue.venueName}</h1>

          <p className="venue-details-location">📍 {venue.city}</p>

          <div className="venue-details-info">
            <div>
              <strong>📍 Address</strong>

              <span>{venue.address}</span>
            </div>

            <div>
              <strong>🏙️ City</strong>

              <span>{venue.city}</span>
            </div>

            <div>
              <strong>👥 Capacity</strong>

              <span>{venue.capacity} people</span>
            </div>

            <div>
              <strong>💰 Price Per Day</strong>

              <span>₹{venue.pricePerDay.toLocaleString()}</span>
            </div>

            <div>
              <strong>✅ Availability</strong>

              <span className="venue-available">{venue.availability}</span>
            </div>
          </div>

          {/* Description */}

          <div className="venue-details-description">
            <h3>About This Venue</h3>

            <p>
              This EventHub venue is perfect for weddings, birthdays,
              conferences, concerts and other special celebrations. Our venues
              provide comfortable spaces and professional facilities for
              memorable events.
            </p>
          </div>

          {/* Buttons */}

          <div className="venue-details-buttons">
            <button
              className="venue-details-back"
              onClick={() => navigate("/venues")}
            >
              ← Back to Venues
            </button>
          </div>
        </div>
      </div>
    </div>
  );
}

export default VenueDetails;
