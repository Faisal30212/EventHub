import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import "./Dashboard.css";

const API_BASE_URL = "https://eventhub-production-4dcd.up.railway.app";

function Dashboard() {
  const navigate = useNavigate();

  const [bookings, setBookings] = useState([]);
  const [loading, setLoading] = useState(true);
  const [message, setMessage] = useState("");

  // Load all customer event requests
  const loadBookings = async () => {
    try {
      setLoading(true);
      setMessage("");

      const response = await fetch(`${API_BASE_URL}/bookings`);

      if (!response.ok) {
        throw new Error("Unable to load booking requests.");
      }

      const data = await response.json();

      if (Array.isArray(data)) {
        setBookings(data);
      } else {
        setBookings([]);
      }
    } catch (error) {
      console.error("Dashboard error:", error);

      setMessage(
        "Unable to load booking requests. Please make sure Spring Boot is running.",
      );
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    loadBookings();
  }, []);

  // Count requests by status
  const requestedCount = bookings.filter(
    (booking) => booking.bookingStatus === "REQUESTED",
  ).length;

  const acceptedCount = bookings.filter(
    (booking) => booking.bookingStatus === "ACCEPTED",
  ).length;

  const completedCount = bookings.filter(
    (booking) => booking.bookingStatus === "COMPLETED",
  ).length;

  return (
    <div className="dashboard-page">
      {/* HEADER */}
      <header className="dashboard-header">
        <div>
          <span className="dashboard-label">EVENTHUB</span>

          <h1>Event Manager Dashboard</h1>

          <p>
            Manage customer event planning requests and track their progress.
          </p>
        </div>

        <button
          type="button"
          className="dashboard-back-button"
          onClick={() => navigate("/")}
        >
          ← Home
        </button>
      </header>

      <main className="dashboard-container">
        {/* SUMMARY CARDS */}
        <section className="dashboard-summary">
          <div className="summary-card">
            <div className="summary-icon">📋</div>

            <div>
              <span>Total Requests</span>
              <strong>{bookings.length}</strong>
            </div>
          </div>

          <div className="summary-card">
            <div className="summary-icon">⏳</div>

            <div>
              <span>New Requests</span>
              <strong>{requestedCount}</strong>
            </div>
          </div>

          <div className="summary-card">
            <div className="summary-icon">✅</div>

            <div>
              <span>Accepted</span>
              <strong>{acceptedCount}</strong>
            </div>
          </div>

          <div className="summary-card">
            <div className="summary-icon">🎉</div>

            <div>
              <span>Completed</span>
              <strong>{completedCount}</strong>
            </div>
          </div>
        </section>

        {/* REQUESTS SECTION */}
        <section className="requests-section">
          <div className="requests-header">
            <div>
              <span className="section-label">CUSTOMER REQUESTS</span>

              <h2>Event Planning Requests</h2>

              <p>Review and manage requests submitted by customers.</p>
            </div>

            <button
              type="button"
              className="refresh-button"
              onClick={loadBookings}
            >
              ↻ Refresh
            </button>
          </div>

          {loading && (
            <div className="dashboard-message">Loading booking requests...</div>
          )}

          {!loading && message && (
            <div className="dashboard-error">{message}</div>
          )}

          {!loading && !message && bookings.length === 0 && (
            <div className="dashboard-message">No event requests found.</div>
          )}

          {!loading && bookings.length > 0 && (
            <div className="booking-table-wrapper">
              <table className="booking-table">
                <thead>
                  <tr>
                    <th>Booking</th>
                    <th>Customer</th>
                    <th>Event</th>
                    <th>Venue</th>
                    <th>Event Date</th>
                    <th>Time</th>
                    <th>Guests</th>
                    <th>Amount</th>
                    <th>Status</th>
                    <th>Requirements</th>
                  </tr>
                </thead>

                <tbody>
                  {bookings.map((booking) => (
                    <tr key={booking.bookingId}>
                      <td>
                        <strong>#{booking.bookingId}</strong>
                      </td>

                      <td>User #{booking.userId}</td>

                      <td>Event #{booking.eventId}</td>

                      <td>Venue #{booking.venueId}</td>

                      <td>{booking.eventDate || "-"}</td>

                      <td>{booking.eventTime || "-"}</td>

                      <td>{booking.guestCount ?? "-"}</td>

                      <td>
                        ₹
                        {Number(booking.totalAmount || 0).toLocaleString(
                          "en-IN",
                        )}
                      </td>

                      <td>
                        <span
                          className={`status-badge status-${String(
                            booking.bookingStatus || "UNKNOWN",
                          ).toLowerCase()}`}
                        >
                          {booking.bookingStatus || "UNKNOWN"}
                        </span>
                      </td>

                      <td className="requirements-cell">
                        {booking.requirements || "No additional requirements"}
                      </td>
                    </tr>
                  ))}
                </tbody>
              </table>
            </div>
          )}
        </section>
      </main>
    </div>
  );
}

export default Dashboard;
