import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import "./Booking.css";

const API_BASE_URL = "http://localhost:8080";

function Booking() {
  const navigate = useNavigate();

  const [events, setEvents] = useState([]);
  const [venues, setVenues] = useState([]);

  const [selectedEventId, setSelectedEventId] = useState("");
  const [selectedEventName, setSelectedEventName] = useState("");

  const [selectedCity, setSelectedCity] = useState("");
  const [selectedVenueId, setSelectedVenueId] = useState("");

  const [eventDate, setEventDate] = useState("");
  const [eventTime, setEventTime] = useState("");

  const [guestCount, setGuestCount] = useState(50);
  const [requirements, setRequirements] = useState("");

  const [loading, setLoading] = useState(true);
  const [submitting, setSubmitting] = useState(false);

  const [message, setMessage] = useState("");
  const [success, setSuccess] = useState(false);

  const eventTypes = [
    {
      name: "Wedding Event",
      category: "WEDDING",
      icon: "💍",
    },
    {
      name: "Birthday Event",
      category: "BIRTHDAY",
      icon: "🎂",
    },
    {
      name: "Conference Event",
      category: "CONFERENCE",
      icon: "💼",
    },
    {
      name: "Concert Event",
      category: "CONCERT",
      icon: "🎵",
    },
    {
      name: "Sports Event",
      category: "SPORTS",
      icon: "🏆",
    },
    {
      name: "College Fest",
      category: "COLLEGE_FEST",
      icon: "🎓",
    },
  ];

  const cityList = ["Bengaluru", "Mysuru", "Shivamogga", "Hassan"];

  // Load events and venues independently
  useEffect(() => {
    const loadEvents = async () => {
      try {
        const response = await fetch(`${API_BASE_URL}/events`);

        if (!response.ok) {
          throw new Error(`Events API returned ${response.status}`);
        }

        const data = await response.json();

        if (Array.isArray(data)) {
          setEvents(data);
          console.log("EVENTS LOADED:", data);
        } else {
          console.error("Events response is not an array:", data);
        }
      } catch (error) {
        console.error("EVENTS LOAD ERROR:", error);
      }
    };

    const loadVenues = async () => {
      try {
        const response = await fetch(`${API_BASE_URL}/venues`);

        if (!response.ok) {
          throw new Error(`Venues API returned ${response.status}`);
        }

        const data = await response.json();

        if (Array.isArray(data)) {
          setVenues(data);
          console.log("VENUES LOADED:", data);
        } else {
          console.error("Venues response is not an array:", data);
        }
      } catch (error) {
        console.error("VENUES LOAD ERROR:", error);

        setMessage(
          "Unable to load venues. Please check the backend connection and CORS settings.",
        );
      }
    };

    const loadAllData = async () => {
      setLoading(true);

      await Promise.allSettled([loadEvents(), loadVenues()]);

      setLoading(false);
    };

    loadAllData();
  }, []);

  // Find backend event using category
  const getBackendEvent = (category) => {
    return events.find(
      (event) => String(event.category).toUpperCase() === category,
    );
  };

  // Select event type
  const handleEventSelect = (eventType) => {
    const backendEvent = getBackendEvent(eventType.category);

    if (backendEvent) {
      setSelectedEventId(String(backendEvent.eventId));
    } else {
      setSelectedEventId("");
      setMessage(`${eventType.name} is currently unavailable.`);
    }

    setSelectedEventName(eventType.name);
    setSuccess(false);
  };

  // Select city
  const handleCitySelect = (city) => {
    setSelectedCity(city);

    // Reset venue when city changes
    setSelectedVenueId("");

    setMessage("");
    setSuccess(false);
  };

  // Filter venues according to selected city
  const filteredVenues = venues.filter(
    (venue) =>
      String(venue.city || "")
        .trim()
        .toLowerCase() ===
      String(selectedCity || "")
        .trim()
        .toLowerCase(),
  );

  // Selected venue
  const selectedVenue = venues.find(
    (venue) => Number(venue.venueId) === Number(selectedVenueId),
  );

  // Selected event
  const selectedEvent = events.find(
    (event) => Number(event.eventId) === Number(selectedEventId),
  );

  const packagePrice = Number(selectedEvent?.basePrice || 0);

  const venuePrice = Number(selectedVenue?.pricePerDay || 0);

  const estimatedTotal = packagePrice + venuePrice;

  // Guest controls
  const decreaseGuests = () => {
    setGuestCount((current) => Math.max(1, Number(current) - 10));
  };

  const increaseGuests = () => {
    const maximum = selectedVenue?.capacity || selectedEvent?.capacity || 10000;

    setGuestCount((current) => Math.min(maximum, Number(current) + 10));
  };

  // Submit event request
  const handleSubmit = async (e) => {
    e.preventDefault();

    setMessage("");
    setSuccess(false);

    const storedUser = localStorage.getItem("user");

    if (!storedUser) {
      setMessage("Please login before submitting an event request.");
      return;
    }

    let user;

    try {
      user = JSON.parse(storedUser);
    } catch (error) {
      console.error(error);

      setMessage("Your login session is invalid. Please login again.");
      return;
    }

    if (!user.userId) {
      setMessage("Please login again before submitting your request.");
      return;
    }

    if (!selectedEventId) {
      setMessage("Please select an event type.");
      return;
    }

    if (!selectedCity) {
      setMessage("Please select a city.");
      return;
    }

    if (!selectedVenueId) {
      setMessage("Please select a venue.");
      return;
    }

    if (!eventDate) {
      setMessage("Please select the event date.");
      return;
    }

    if (!eventTime) {
      setMessage("Please select the event time.");
      return;
    }

    if (!guestCount || guestCount < 1) {
      setMessage("Please enter the number of guests.");
      return;
    }

    if (selectedVenue && Number(guestCount) > Number(selectedVenue.capacity)) {
      setMessage(
        `This venue can accommodate maximum ${selectedVenue.capacity} guests.`,
      );
      return;
    }

    const bookingData = {
      userId: Number(user.userId),
      eventId: Number(selectedEventId),
      venueId: Number(selectedVenueId),
      eventDate: eventDate,
      eventTime: eventTime,
      guestCount: Number(guestCount),
      requirements: requirements,
    };

    console.log("EVENT REQUEST:", bookingData);

    setSubmitting(true);

    try {
      const response = await fetch(`${API_BASE_URL}/bookings/add`, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(bookingData),
      });

      const responseText = await response.text();

      if (!response.ok) {
        throw new Error(responseText || "Unable to submit event request.");
      }

      setSuccess(true);

      setMessage(
        "Event request submitted successfully! Our Event Manager will review your request and contact you.",
      );

      // Reset form after successful submission
      setSelectedEventId("");
      setSelectedEventName("");
      setSelectedCity("");
      setSelectedVenueId("");
      setEventDate("");
      setEventTime("");
      setGuestCount(50);
      setRequirements("");
    } catch (error) {
      console.error("Request submission error:", error);

      setSuccess(false);

      setMessage(error.message || "Unable to submit your request.");
    } finally {
      setSubmitting(false);
    }
  };

  if (loading) {
    return (
      <div className="booking-page">
        <div className="booking-loading">Loading EventHub...</div>
      </div>
    );
  }

  return (
    <div className="booking-page">
      <div className="booking-container">
        <div className="booking-header">
          <h1>Plan Your Event 🎉</h1>

          <p>Choose your event type, city, venue, date and requirements.</p>
        </div>

        <form className="booking-card" onSubmit={handleSubmit}>
          {/* EVENT TYPE */}

          <section className="booking-section">
            <h2>1. Choose Event Type</h2>

            <p className="section-description">
              Select the type of event you want EventHub to organize.
            </p>

            <div className="selection-grid">
              {eventTypes.map((eventType) => {
                const backendEvent = getBackendEvent(eventType.category);

                const isSelected = selectedEventName === eventType.name;

                return (
                  <button
                    type="button"
                    key={eventType.category}
                    className={
                      isSelected ? "selection-card selected" : "selection-card"
                    }
                    onClick={() => handleEventSelect(eventType)}
                  >
                    <span className="selection-icon">{eventType.icon}</span>

                    <strong>{eventType.name}</strong>

                    <small>
                      {backendEvent
                        ? `Starting ₹${Number(
                            backendEvent.basePrice || 0,
                          ).toLocaleString("en-IN")}`
                        : "Event Management"}
                    </small>
                  </button>
                );
              })}
            </div>

            {selectedEvent && (
              <div className="selected-information">
                <h3>{selectedEvent.eventName}</h3>

                <p>{selectedEvent.description}</p>

                <div className="info-grid">
                  <div>
                    <span>Starting Package</span>

                    <strong>₹{packagePrice.toLocaleString("en-IN")}</strong>
                  </div>

                  <div>
                    <span>Duration</span>

                    <strong>
                      {selectedEvent.durationHours || "Flexible"} hours
                    </strong>
                  </div>

                  <div>
                    <span>Maximum Guests</span>

                    <strong>{selectedEvent.capacity}</strong>
                  </div>
                </div>
              </div>
            )}
          </section>

          {/* CITY */}

          <section className="booking-section">
            <h2>2. Choose City</h2>

            <p className="section-description">
              Select where you want your event to be organized.
            </p>

            <div className="city-grid">
              {cityList.map((city) => (
                <button
                  type="button"
                  key={city}
                  className={
                    selectedCity === city ? "city-card selected" : "city-card"
                  }
                  onClick={() => handleCitySelect(city)}
                >
                  <span>📍</span>

                  <strong>{city}</strong>
                </button>
              ))}
            </div>
          </section>

          {/* VENUE */}

          <section className="booking-section">
            <h2>3. Choose Venue</h2>

            <p className="section-description">
              Select one of our available venues.
            </p>

            {!selectedCity && (
              <div className="empty-selection">Please select a city first.</div>
            )}

            {selectedCity && filteredVenues.length === 0 && (
              <div className="empty-selection">
                No venues available in <strong>{selectedCity}</strong>.
                <br />
                <br />
                Venues received from backend: {venues.length}
              </div>
            )}

            {filteredVenues.length > 0 && (
              <div className="venue-selection-grid">
                {filteredVenues.map((venue) => (
                  <button
                    type="button"
                    key={venue.venueId}
                    className={
                      Number(selectedVenueId) === Number(venue.venueId)
                        ? "venue-card selected"
                        : "venue-card"
                    }
                    onClick={() => setSelectedVenueId(String(venue.venueId))}
                  >
                    <div className="venue-card-icon">🏛️</div>

                    <h3>{venue.venueName}</h3>

                    <p>📍 {venue.address}</p>

                    <div className="venue-details">
                      <span>👥 {venue.capacity} guests</span>

                      <span>
                        ₹
                        {Number(venue.pricePerDay || 0).toLocaleString("en-IN")}
                        /day
                      </span>
                    </div>
                  </button>
                ))}
              </div>
            )}
          </section>

          {/* DATE AND TIME */}

          <section className="booking-section">
            <h2>4. Event Date & Time</h2>

            <div className="date-time-row">
              <div>
                <label>Event Date</label>

                <input
                  type="date"
                  value={eventDate}
                  min={new Date().toISOString().split("T")[0]}
                  onChange={(e) => setEventDate(e.target.value)}
                />
              </div>

              <div>
                <label>Event Time</label>

                <input
                  type="time"
                  value={eventTime}
                  onChange={(e) => setEventTime(e.target.value)}
                />
              </div>
            </div>
          </section>

          {/* GUEST COUNT */}

          <section className="booking-section">
            <h2>5. Number of Guests</h2>

            <label>Expected Number of Guests</label>

            <div className="guest-control">
              <button type="button" onClick={decreaseGuests}>
                −
              </button>

              <input
                type="number"
                min="1"
                value={guestCount}
                onChange={(e) => setGuestCount(Number(e.target.value))}
              />

              <button type="button" onClick={increaseGuests}>
                +
              </button>
            </div>

            {selectedVenue && (
              <small>Maximum capacity: {selectedVenue.capacity} guests</small>
            )}
          </section>

          {/* REQUIREMENTS */}

          <section className="booking-section">
            <h2>6. Additional Requirements</h2>

            <label>Tell us what you need</label>

            <textarea
              rows="5"
              value={requirements}
              onChange={(e) => setRequirements(e.target.value)}
              placeholder="Example: Stage decoration, catering, sound system, photography..."
            />
          </section>

          {/* ESTIMATE */}

          {selectedEvent && selectedVenue && (
            <div className="estimate-box">
              <h2>Estimated Cost</h2>

              <div className="estimate-row">
                <span>Event Management Package</span>

                <strong>₹{packagePrice.toLocaleString("en-IN")}</strong>
              </div>

              <div className="estimate-row">
                <span>Venue Fee</span>

                <strong>₹{venuePrice.toLocaleString("en-IN")}</strong>
              </div>

              <div className="estimate-total">
                <span>Estimated Total</span>

                <strong>₹{estimatedTotal.toLocaleString("en-IN")}</strong>
              </div>

              <p>
                This is an initial estimate. The final quote will be prepared by
                the Event Manager after discussing your requirements.
              </p>
            </div>
          )}

          {/* MESSAGE */}

          {message && (
            <div className={success ? "booking-success" : "booking-error"}>
              {message}
            </div>
          )}

          {/* SUBMIT */}

          <button
            type="submit"
            className="submit-booking-button"
            disabled={submitting}
          >
            {submitting ? "Submitting Request..." : "Submit Event Request"}
          </button>
        </form>

        <button
          type="button"
          className="back-button"
          onClick={() => navigate(-1)}
        >
          ← Back
        </button>
      </div>
    </div>
  );
}

export default Booking;
