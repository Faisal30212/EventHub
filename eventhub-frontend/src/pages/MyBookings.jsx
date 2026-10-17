import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import "./MyBookings.css";

const API_BASE_URL = "https://eventhub-production-4dcd.up.railway.app";

function MyBookings() {
  const navigate = useNavigate();

  const [user, setUser] = useState(null);
  const [bookings, setBookings] = useState([]);

  const [loading, setLoading] = useState(true);
  const [message, setMessage] = useState("");

  useEffect(() => {
    const storedUser =
      localStorage.getItem("user") || localStorage.getItem("eventhubUser");

    if (!storedUser) {
      navigate("/login");
      return;
    }

    try {
      const parsedUser = JSON.parse(storedUser);

      if (!parsedUser.userId) {
        navigate("/login");
        return;
      }

      setUser(parsedUser);
    } catch (error) {
      console.error("User session error:", error);
      navigate("/login");
    }
  }, [navigate]);

  const loadBookings = async () => {
    if (!user) {
      return;
    }

    try {
      setLoading(true);
      setMessage("");

      const response = await fetch(`${API_BASE_URL}/bookings`);

      if (!response.ok) {
        throw new Error("Unable to load your event requests.");
      }

      const data = await response.json();

      const allBookings = Array.isArray(data) ? data : [];

      // Show only bookings belonging to the logged-in customer.
      const customerBookings = allBookings.filter((booking) => {
        const bookingUserId = booking.user?.userId ?? booking.userId;

        return Number(bookingUserId) === Number(user.userId);
      });

      // Latest booking first.
      customerBookings.sort(
        (a, b) => Number(b.bookingId || 0) - Number(a.bookingId || 0),
      );

      setBookings(customerBookings);
    } catch (error) {
      console.error("My bookings error:", error);

      setMessage(
        "Unable to load your event requests. Please make sure Spring Boot is running.",
      );
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    if (user) {
      loadBookings();
    }
  }, [user]);

  const getStatusClass = (status) => {
    return `my-booking-status status-${String(
      status || "UNKNOWN",
    ).toLowerCase()}`;
  };

  const canViewBill = (booking) => {
    return (
      booking.bookingStatus === "PAYMENT_COMPLETED" ||
      Boolean(booking.billNumber)
    );
  };

  return (
    <div className="my-bookings-page">
      {/* HEADER */}

      <header className="my-bookings-header">
        <div>
          <span className="my-bookings-label">EVENTHUB</span>

          <h1>My Events</h1>

          <p>Welcome, {user?.fullName || "Customer"} 👋</p>
        </div>

        <div className="my-bookings-header-actions">
          <button
            type="button"
            onClick={() => navigate("/bookings")}
            className="new-event-btn"
          >
            + Plan Another Event
          </button>

          <button
            type="button"
            onClick={() => navigate("/")}
            className="home-btn"
          >
            Home
          </button>
        </div>
      </header>

      <main className="my-bookings-container">
        {message && <div className="my-bookings-message">{message}</div>}

        {loading ? (
          <div className="my-bookings-empty">
            Loading your event requests...
          </div>
        ) : bookings.length === 0 ? (
          <div className="my-bookings-empty">
            <div className="empty-icon">🎉</div>

            <h2>No event requests yet</h2>

            <p>You have not submitted any event planning requests yet.</p>

            <button
              type="button"
              onClick={() => navigate("/bookings")}
              className="plan-event-btn"
            >
              Plan My Event
            </button>
          </div>
        ) : (
          <section className="my-bookings-list">
            <div className="my-bookings-section-heading">
              <div>
                <span>YOUR EVENTS</span>

                <h2>Event Requests</h2>

                <p>
                  Track your event planning request and view your bill after
                  payment.
                </p>
              </div>

              <button
                type="button"
                className="refresh-bookings-btn"
                onClick={loadBookings}
              >
                ↻ Refresh
              </button>
            </div>

            <div className="customer-booking-grid">
              {bookings.map((booking) => {
                const eventName =
                  booking.event?.eventName || booking.eventName || "Event";

                const venueName =
                  booking.venue?.venueName || booking.venueName || "Venue";

                const city = booking.venue?.city || booking.city || "-";

                const amount = Number(
                  booking.quoteAmount || booking.totalAmount || 0,
                );

                return (
                  <article
                    className="customer-booking-card"
                    key={booking.bookingId}
                  >
                    {/* TOP */}

                    <div className="customer-booking-top">
                      <div>
                        <span className="request-label">EVENT REQUEST</span>

                        <h3>{eventName}</h3>
                      </div>

                      <span className={getStatusClass(booking.bookingStatus)}>
                        {booking.bookingStatus || "UNKNOWN"}
                      </span>
                    </div>

                    {/* DETAILS */}

                    <div className="customer-booking-details">
                      <div>
                        <span>Request ID</span>

                        <strong>#{booking.bookingId}</strong>
                      </div>

                      <div>
                        <span>Venue</span>

                        <strong>{venueName}</strong>
                      </div>

                      <div>
                        <span>City</span>

                        <strong>{city}</strong>
                      </div>

                      <div>
                        <span>Event Date</span>

                        <strong>{booking.eventDate || "-"}</strong>
                      </div>

                      <div>
                        <span>Event Time</span>

                        <strong>{booking.eventTime || "-"}</strong>
                      </div>

                      <div>
                        <span>Guests</span>

                        <strong>{booking.guestCount || "-"}</strong>
                      </div>
                    </div>

                    {/* QUOTE */}

                    {booking.quoteAmount && (
                      <div className="customer-quote-box">
                        <div>
                          <span>Final Quote</span>

                          <strong>₹{amount.toLocaleString("en-IN")}</strong>
                        </div>

                        <p>
                          {booking.quoteDetails ||
                            "Final event quotation prepared by EventHub."}
                        </p>
                      </div>
                    )}

                    {/* PAYMENT / BILL */}

                    {canViewBill(booking) ? (
                      <div className="customer-payment-box">
                        <div className="paid-information">
                          <span>✅ PAYMENT COMPLETED</span>

                          <strong>
                            {booking.billNumber ||
                              `EH-BILL-${booking.bookingId}`}
                          </strong>
                        </div>

                        <button
                          type="button"
                          className="view-bill-btn"
                          onClick={() => navigate(`/bill/${booking.bookingId}`)}
                        >
                          🧾 View Bill
                        </button>
                      </div>
                    ) : (
                      <div className="customer-progress-box">
                        <span>📌 EventHub Update</span>

                        <p>
                          Your request is being processed by our Event Manager.
                        </p>
                      </div>
                    )}
                  </article>
                );
              })}
            </div>
          </section>
        )}
      </main>
    </div>
  );
}

export default MyBookings;
