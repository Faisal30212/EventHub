import { useEffect, useMemo, useState } from "react";
import { useNavigate } from "react-router-dom";
import "./ManagerDashboard.css";

const API_BASE_URL = "https://eventhub-production-4dcd.up.railway.app";

function ManagerDashboard() {
  const navigate = useNavigate();

  const [manager, setManager] = useState(null);
  const [bookings, setBookings] = useState([]);

  const [loading, setLoading] = useState(true);
  const [saving, setSaving] = useState(false);

  const [message, setMessage] = useState("");
  const [messageType, setMessageType] = useState("");

  const [selectedBooking, setSelectedBooking] = useState(null);

  const [appointmentDate, setAppointmentDate] = useState("");
  const [appointmentTime, setAppointmentTime] = useState("");
  const [meetingNotes, setMeetingNotes] = useState("");

  const [quoteAmount, setQuoteAmount] = useState("");
  const [quoteDetails, setQuoteDetails] = useState("");

  // ==========================================
  // LOAD LOGGED-IN MANAGER
  // ==========================================

  useEffect(() => {
    const storedUser =
      localStorage.getItem("eventhubUser") || localStorage.getItem("user");

    if (!storedUser) {
      navigate("/staff-login");
      return;
    }

    try {
      const parsedUser = JSON.parse(storedUser);

      const role = String(parsedUser.role || "").toUpperCase();

      if (role !== "CITY_MANAGER" && role !== "HEAD_MANAGER") {
        navigate("/login");
        return;
      }

      setManager(parsedUser);
    } catch (error) {
      console.error("Manager session error:", error);
      navigate("/staff-login");
    }
  }, [navigate]);

  // ==========================================
  // LOAD ALL BOOKINGS
  // ==========================================

  const loadBookings = async () => {
    try {
      setLoading(true);
      setMessage("");

      const response = await fetch(`${API_BASE_URL}/bookings`);

      if (!response.ok) {
        throw new Error("Unable to load event requests.");
      }

      const data = await response.json();

      setBookings(Array.isArray(data) ? data : []);
    } catch (error) {
      console.error("Booking loading error:", error);

      setMessage(
        "Unable to load event requests. Please make sure Spring Boot is running.",
      );

      setMessageType("error");
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    if (manager) {
      loadBookings();
    }
  }, [manager]);

  // ==========================================
  // CITY FILTER
  // ==========================================

  const visibleBookings = useMemo(() => {
    if (!manager) {
      return [];
    }

    const role = String(manager.role || "").toUpperCase();

    // Head Manager sees everything.
    if (role === "HEAD_MANAGER") {
      return bookings;
    }

    const managerCity = String(manager.city || "")
      .trim()
      .toLowerCase();

    return bookings.filter((booking) => {
      const bookingCity = String(booking.venue?.city || booking.city || "")
        .trim()
        .toLowerCase();

      return bookingCity === managerCity;
    });
  }, [bookings, manager]);

  // ==========================================
  // COUNTS
  // ==========================================

  const requestedCount = visibleBookings.filter(
    (booking) => booking.bookingStatus === "REQUESTED",
  ).length;

  const acceptedCount = visibleBookings.filter(
    (booking) => booking.bookingStatus === "ACCEPTED",
  ).length;

  const appointmentCount = visibleBookings.filter(
    (booking) => booking.bookingStatus === "APPOINTMENT_FIXED",
  ).length;

  const completedCount = visibleBookings.filter(
    (booking) =>
      booking.bookingStatus === "COMPLETED" ||
      booking.bookingStatus === "PAYMENT_COMPLETED",
  ).length;

  // ==========================================
  // OPEN BOOKING
  // ==========================================

  const openBooking = (booking) => {
    setSelectedBooking(booking);

    setAppointmentDate(booking.appointmentDate || "");
    setAppointmentTime(booking.appointmentTime || "");
    setMeetingNotes(booking.meetingNotes || "");

    setQuoteAmount(
      booking.quoteAmount !== null && booking.quoteAmount !== undefined
        ? booking.quoteAmount
        : "",
    );

    setQuoteDetails(booking.quoteDetails || "");

    setMessage("");
    setMessageType("");
  };

  // ==========================================
  // CLOSE BOOKING
  // ==========================================

  const closeBooking = () => {
    setSelectedBooking(null);

    setAppointmentDate("");
    setAppointmentTime("");
    setMeetingNotes("");
    setQuoteAmount("");
    setQuoteDetails("");

    setMessage("");
    setMessageType("");
  };

  // ==========================================
  // MANAGE BOOKING
  // ==========================================

  const updateBooking = async (status) => {
    if (!selectedBooking) {
      return;
    }

    const bookingData = {
      eventId:
        selectedBooking.event?.eventId || selectedBooking.eventId || null,

      venueId:
        selectedBooking.venue?.venueId || selectedBooking.venueId || null,

      eventDate: selectedBooking.eventDate || null,

      eventTime: selectedBooking.eventTime || null,

      guestCount:
        selectedBooking.guestCount || selectedBooking.numberOfGuests || 1,

      requirements: selectedBooking.requirements || "",

      appointmentDate: appointmentDate || null,

      appointmentTime: appointmentTime || null,

      meetingNotes: meetingNotes || "",

      quoteAmount:
        quoteAmount !== "" && quoteAmount !== null ? Number(quoteAmount) : null,

      quoteDetails: quoteDetails || "",

      bookingStatus: status,
    };

    try {
      setSaving(true);
      setMessage("");

      const response = await fetch(
        `${API_BASE_URL}/bookings/manage/${selectedBooking.bookingId}`,
        {
          method: "PUT",

          headers: {
            "Content-Type": "application/json",
          },

          body: JSON.stringify(bookingData),
        },
      );

      const responseText = await response.text();

      if (!response.ok) {
        throw new Error(responseText || "Unable to update booking.");
      }

      setMessage("Booking updated successfully.");
      setMessageType("success");

      await loadBookings();

      // Update selected booking status locally.
      setSelectedBooking((previous) => ({
        ...previous,
        bookingStatus: status,
        appointmentDate,
        appointmentTime,
        meetingNotes,
        quoteAmount: quoteAmount !== "" ? Number(quoteAmount) : null,
        quoteDetails,
      }));
    } catch (error) {
      console.error("Booking update error:", error);

      setMessage(error.message || "Unable to update booking.");

      setMessageType("error");
    } finally {
      setSaving(false);
    }
  };

  // ==========================================
  // ACCEPT
  // ==========================================

  const acceptBooking = () => {
    updateBooking("ACCEPTED");
  };

  // ==========================================
  // REJECT
  // ==========================================

  const rejectBooking = () => {
    updateBooking("REJECTED");
  };

  // ==========================================
  // FIX APPOINTMENT
  // ==========================================

  const fixAppointment = () => {
    if (!appointmentDate) {
      setMessage("Please select the appointment date.");
      setMessageType("error");
      return;
    }

    if (!appointmentTime) {
      setMessage("Please select the appointment time.");
      setMessageType("error");
      return;
    }

    updateBooking("APPOINTMENT_FIXED");
  };

  // ==========================================
  // COMPLETE MEETING
  // ==========================================

  const completeMeeting = () => {
    if (!meetingNotes.trim()) {
      setMessage("Please enter meeting notes first.");
      setMessageType("error");
      return;
    }

    updateBooking("MEETING_COMPLETED");
  };

  // ==========================================
  // GENERATE QUOTE
  // ==========================================

  const generateQuote = () => {
    if (
      quoteAmount === "" ||
      quoteAmount === null ||
      Number(quoteAmount) <= 0
    ) {
      setMessage("Please enter a valid final quote amount.");
      setMessageType("error");
      return;
    }

    updateBooking("QUOTE_GENERATED");
  };

  // ==========================================
  // MARK PAYMENT COMPLETED
  // ==========================================

  const markPaymentCompleted = async () => {
    if (!selectedBooking) {
      return;
    }

    const confirmPayment = window.confirm(
      "Confirm that the customer has paid the final amount at the EventHub office?",
    );

    if (!confirmPayment) {
      return;
    }

    try {
      setSaving(true);
      setMessage("");

      const response = await fetch(
        `${API_BASE_URL}/payments/mark-paid/${selectedBooking.bookingId}`,
        {
          method: "PUT",
          headers: {
            "Content-Type": "application/json",
          },
        },
      );

      const responseText = await response.text();

      if (!response.ok) {
        throw new Error(responseText || "Unable to complete payment.");
      }

      setMessage("Payment completed successfully. Bill generated.");

      setMessageType("success");

      await loadBookings();

      setSelectedBooking((previous) => ({
        ...previous,
        bookingStatus: "PAYMENT_COMPLETED",
      }));
    } catch (error) {
      console.error("Payment error:", error);

      setMessage(error.message || "Unable to complete payment.");

      setMessageType("error");
    } finally {
      setSaving(false);
    }
  };

  // ==========================================
  // LOGOUT
  // ==========================================

  const logout = () => {
    localStorage.removeItem("user");
    localStorage.removeItem("eventhubUser");

    navigate("/staff-login");
  };

  // ==========================================
  // MANAGER DATA NOT READY
  // ==========================================

  if (!manager) {
    return (
      <div className="manager-loading-page">Loading manager dashboard...</div>
    );
  }

  const isHeadManager = String(manager.role).toUpperCase() === "HEAD_MANAGER";

  // ==========================================
  // UI
  // ==========================================

  return (
    <div className="manager-dashboard">
      {/* =====================================
          HEADER
      ====================================== */}

      <header className="manager-header">
        <div>
          <span className="manager-label">EVENTHUB</span>

          <h1>Hi, {manager.fullName} 👋</h1>

          <p>Welcome to your EventHub management dashboard.</p>

          <div className="manager-role">
            {isHeadManager
              ? "Head Manager • All Cities"
              : `${manager.city} City Manager`}
          </div>
        </div>

        <button type="button" className="manager-logout-btn" onClick={logout}>
          Logout
        </button>
      </header>

      <main className="manager-container">
        {/* ===================================
            SUMMARY
        ==================================== */}

        <section className="manager-summary">
          <div className="manager-card">
            <span className="manager-card-icon">📋</span>

            <div>
              <small>Total Requests</small>

              <strong>{visibleBookings.length}</strong>
            </div>
          </div>

          <div className="manager-card">
            <span className="manager-card-icon">⏳</span>

            <div>
              <small>New Requests</small>

              <strong>{requestedCount}</strong>
            </div>
          </div>

          <div className="manager-card">
            <span className="manager-card-icon">📅</span>

            <div>
              <small>Appointments</small>

              <strong>{appointmentCount}</strong>
            </div>
          </div>

          <div className="manager-card">
            <span className="manager-card-icon">✅</span>

            <div>
              <small>Completed</small>

              <strong>{completedCount}</strong>
            </div>
          </div>
        </section>

        {/* ===================================
            REQUESTS
        ==================================== */}

        <section className="manager-requests">
          <div className="manager-section-heading">
            <div>
              <span>MANAGEMENT</span>

              <h2>
                {isHeadManager
                  ? "All Event Requests"
                  : `${manager.city} Event Requests`}
              </h2>

              <p>
                Review customer requests and manage their complete event
                planning process.
              </p>
            </div>

            <button
              type="button"
              className="manager-refresh-btn"
              onClick={loadBookings}
            >
              ↻ Refresh
            </button>
          </div>

          {message && (
            <div
              className={
                messageType === "error"
                  ? "manager-message manager-message-error"
                  : "manager-message manager-message-success"
              }
            >
              {message}
            </div>
          )}

          {loading ? (
            <div className="manager-empty">Loading event requests...</div>
          ) : visibleBookings.length === 0 ? (
            <div className="manager-empty">
              No event requests found
              {isHeadManager ? "." : ` for ${manager.city}.`}
            </div>
          ) : (
            <div className="manager-table-wrapper">
              <table className="manager-table">
                <thead>
                  <tr>
                    <th>Request</th>

                    <th>Customer</th>

                    <th>Event</th>

                    <th>Venue</th>

                    <th>Date</th>

                    <th>Guests</th>

                    <th>Status</th>

                    <th>Action</th>
                  </tr>
                </thead>

                <tbody>
                  {visibleBookings.map((booking) => (
                    <tr key={booking.bookingId}>
                      <td>
                        <strong>#{booking.bookingId}</strong>
                      </td>

                      <td>
                        {booking.user?.fullName ||
                          booking.customerName ||
                          `User #${
                            booking.user?.userId || booking.userId || "-"
                          }`}
                      </td>

                      <td>
                        {booking.event?.eventName ||
                          booking.eventName ||
                          `Event #${
                            booking.event?.eventId || booking.eventId || "-"
                          }`}
                      </td>

                      <td>
                        {booking.venue?.venueName ||
                          booking.venueName ||
                          `Venue #${
                            booking.venue?.venueId || booking.venueId || "-"
                          }`}
                      </td>

                      <td>{booking.eventDate || "-"}</td>

                      <td>{booking.guestCount || "-"}</td>

                      <td>
                        <span
                          className={`manager-status status-${String(
                            booking.bookingStatus || "UNKNOWN",
                          ).toLowerCase()}`}
                        >
                          {booking.bookingStatus || "UNKNOWN"}
                        </span>
                      </td>

                      <td>
                        <button
                          type="button"
                          className="manage-booking-btn"
                          onClick={() => openBooking(booking)}
                        >
                          Manage
                        </button>
                      </td>
                    </tr>
                  ))}
                </tbody>
              </table>
            </div>
          )}
        </section>
      </main>

      {/* =====================================
          MANAGEMENT MODAL
      ====================================== */}

      {selectedBooking && (
        <div className="manager-modal-overlay" onClick={closeBooking}>
          <div
            className="manager-modal"
            onClick={(event) => event.stopPropagation()}
          >
            <div className="manager-modal-header">
              <div>
                <span>EVENT REQUEST</span>

                <h2>Request #{selectedBooking.bookingId}</h2>
              </div>

              <button
                type="button"
                className="manager-close-btn"
                onClick={closeBooking}
              >
                ×
              </button>
            </div>

            <div className="manager-details-grid">
              {/* CUSTOMER */}

              <div className="manager-detail-card">
                <h3>Customer</h3>

                <p>
                  <strong>Name:</strong>{" "}
                  {selectedBooking.user?.fullName ||
                    selectedBooking.customerName ||
                    "-"}
                </p>

                <p>
                  <strong>Email:</strong>{" "}
                  {selectedBooking.user?.email ||
                    selectedBooking.customerEmail ||
                    "-"}
                </p>

                <p>
                  <strong>Phone:</strong>{" "}
                  {selectedBooking.user?.phone ||
                    selectedBooking.customerPhone ||
                    "-"}
                </p>
              </div>

              {/* EVENT */}

              <div className="manager-detail-card">
                <h3>Event</h3>

                <p>
                  <strong>Type:</strong>{" "}
                  {selectedBooking.event?.eventName ||
                    selectedBooking.eventName ||
                    "-"}
                </p>

                <p>
                  <strong>Venue:</strong>{" "}
                  {selectedBooking.venue?.venueName ||
                    selectedBooking.venueName ||
                    "-"}
                </p>

                <p>
                  <strong>City:</strong>{" "}
                  {selectedBooking.venue?.city || selectedBooking.city || "-"}
                </p>

                <p>
                  <strong>Guests:</strong> {selectedBooking.guestCount || "-"}
                </p>

                <p>
                  <strong>Event Date:</strong>{" "}
                  {selectedBooking.eventDate || "-"}
                </p>

                <p>
                  <strong>Event Time:</strong>{" "}
                  {selectedBooking.eventTime || "-"}
                </p>
              </div>

              {/* REQUIREMENTS */}

              <div className="manager-detail-card full-width">
                <h3>Customer Requirements</h3>

                <p>
                  {selectedBooking.requirements ||
                    "No additional requirements provided."}
                </p>
              </div>

              {/* EXISTING QUOTE */}

              {selectedBooking.quoteAmount && (
                <div className="manager-detail-card full-width">
                  <h3>Current Quote</h3>

                  <p>
                    <strong>
                      ₹
                      {Number(selectedBooking.quoteAmount).toLocaleString(
                        "en-IN",
                      )}
                    </strong>
                  </p>

                  <p>
                    {selectedBooking.quoteDetails ||
                      "No quote details available."}
                  </p>
                </div>
              )}

              {/* BILL */}

              {selectedBooking.billNumber && (
                <div className="manager-detail-card full-width">
                  <h3>Bill</h3>

                  <p>
                    <strong>Bill Number:</strong> {selectedBooking.billNumber}
                  </p>

                  <p>
                    <strong>Bill Date:</strong>{" "}
                    {selectedBooking.billDate || "-"}
                  </p>
                </div>
              )}
            </div>

            {/* =================================
                ACTION AREA
            ================================== */}

            <div className="manager-action-section">
              <h3>Manager Actions</h3>

              {/* APPOINTMENT */}

              <div className="manager-form-row">
                <div className="manager-form-group">
                  <label>Appointment Date</label>

                  <input
                    type="date"
                    value={appointmentDate}
                    onChange={(event) => setAppointmentDate(event.target.value)}
                  />
                </div>

                <div className="manager-form-group">
                  <label>Appointment Time</label>

                  <input
                    type="time"
                    value={appointmentTime}
                    onChange={(event) => setAppointmentTime(event.target.value)}
                  />
                </div>
              </div>

              {/* MEETING NOTES */}

              <div className="manager-form-group">
                <label>Meeting Notes</label>

                <textarea
                  rows="4"
                  value={meetingNotes}
                  onChange={(event) => setMeetingNotes(event.target.value)}
                  placeholder="Enter customer discussion and requirements..."
                />
              </div>

              {/* QUOTE */}

              <div className="manager-form-group">
                <label>Final Quote Amount</label>

                <input
                  type="number"
                  min="0"
                  value={quoteAmount}
                  onChange={(event) => setQuoteAmount(event.target.value)}
                  placeholder="Enter final quote"
                />
              </div>

              <div className="manager-form-group">
                <label>Quote Details</label>

                <textarea
                  rows="4"
                  value={quoteDetails}
                  onChange={(event) => setQuoteDetails(event.target.value)}
                  placeholder="Venue, decoration, catering, sound system..."
                />
              </div>
            </div>

            {/* =================================
                BUTTONS
            ================================== */}

            <div className="manager-modal-actions">
              {selectedBooking.bookingStatus === "REQUESTED" && (
                <>
                  <button
                    type="button"
                    className="action-reject"
                    onClick={rejectBooking}
                    disabled={saving}
                  >
                    Reject
                  </button>

                  <button
                    type="button"
                    className="action-accept"
                    onClick={acceptBooking}
                    disabled={saving}
                  >
                    Accept
                  </button>
                </>
              )}

              {selectedBooking.bookingStatus === "ACCEPTED" && (
                <button
                  type="button"
                  className="action-appointment"
                  onClick={fixAppointment}
                  disabled={saving}
                >
                  Fix Appointment
                </button>
              )}

              {selectedBooking.bookingStatus === "APPOINTMENT_FIXED" && (
                <button
                  type="button"
                  className="action-meeting"
                  onClick={completeMeeting}
                  disabled={saving}
                >
                  Complete Meeting
                </button>
              )}

              {selectedBooking.bookingStatus === "MEETING_COMPLETED" && (
                <button
                  type="button"
                  className="action-quote"
                  onClick={generateQuote}
                  disabled={saving}
                >
                  Generate Quote
                </button>
              )}

              {selectedBooking.bookingStatus === "QUOTE_GENERATED" && (
                <button
                  type="button"
                  className="action-payment"
                  onClick={markPaymentCompleted}
                  disabled={saving}
                >
                  {saving ? "Processing..." : "Office Payment Received"}
                </button>
              )}

              {selectedBooking.bookingStatus === "PAYMENT_PENDING" && (
                <button
                  type="button"
                  className="action-payment"
                  onClick={markPaymentCompleted}
                  disabled={saving}
                >
                  {saving ? "Processing..." : "Office Payment Received"}
                </button>
              )}

              {selectedBooking.bookingStatus === "PAYMENT_COMPLETED" && (
                <button
                  type="button"
                  className="action-payment"
                  onClick={() => navigate(`/bill/${selectedBooking.bookingId}`)}
                >
                  🧾 View Bill
                </button>
              )}

              <button
                type="button"
                className="action-close"
                onClick={closeBooking}
              >
                Close
              </button>
            </div>
          </div>
        </div>
      )}
    </div>
  );
}

export default ManagerDashboard;
