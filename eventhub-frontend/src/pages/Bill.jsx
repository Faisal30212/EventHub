import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import "./Bill.css";

const API_BASE_URL = "https://eventhub-production-4dcd.up.railway.app";

function Bill() {
  const { bookingId } = useParams();
  const navigate = useNavigate();

  const [booking, setBooking] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  useEffect(() => {
    const loadBooking = async () => {
      try {
        const response = await fetch(`${API_BASE_URL}/bookings/${bookingId}`);

        if (!response.ok) {
          throw new Error("Unable to load bill details.");
        }

        const data = await response.json();

        setBooking(data);
      } catch (err) {
        console.error("Bill loading error:", err);
        setError(err.message || "Unable to load bill.");
      } finally {
        setLoading(false);
      }
    };

    loadBooking();
  }, [bookingId]);

  if (loading) {
    return <div className="bill-loading">Loading bill...</div>;
  }

  if (error || !booking) {
    return (
      <div className="bill-error-page">
        <h2>Bill Not Available</h2>

        <p>{error || "Unable to find this booking."}</p>

        <button type="button" onClick={() => navigate(-1)}>
          ← Go Back
        </button>
      </div>
    );
  }

  const customerName =
    booking.user?.fullName || booking.customerName || "Customer";

  const customerEmail = booking.user?.email || booking.customerEmail || "-";

  const customerPhone = booking.user?.phone || booking.customerPhone || "-";

  const eventName = booking.event?.eventName || booking.eventName || "-";

  const venueName = booking.venue?.venueName || booking.venueName || "-";

  const venueCity = booking.venue?.city || booking.city || "-";

  const billNumber = booking.billNumber || `EH-BILL-${booking.bookingId}`;

  const billDate = booking.billDate || booking.bookingDate || "-";

  const amount = Number(booking.quoteAmount || booking.totalAmount || 0);

  const printBill = () => {
    window.print();
  };

  return (
    <div className="bill-page">
      {/* TOP ACTIONS */}

      <div className="bill-actions no-print">
        <button
          type="button"
          className="bill-back-btn"
          onClick={() => navigate(-1)}
        >
          ← Back
        </button>

        <button type="button" className="bill-print-btn" onClick={printBill}>
          🖨 Print Bill
        </button>
      </div>

      {/* BILL */}

      <div className="bill-card">
        {/* HEADER */}

        <div className="bill-header">
          <div>
            <span className="bill-brand">EVENTHUB</span>

            <h1>Event Management</h1>

            <p>Professional Event Planning & Management</p>
          </div>

          <div className="bill-title">
            <span>PAYMENT BILL</span>

            <strong>{billNumber}</strong>
          </div>
        </div>

        {/* BILL META */}

        <div className="bill-meta">
          <div>
            <span>Bill Date</span>

            <strong>{billDate}</strong>
          </div>

          <div>
            <span>Booking ID</span>

            <strong>#{booking.bookingId}</strong>
          </div>

          <div>
            <span>Payment Method</span>

            <strong>Office</strong>
          </div>

          <div>
            <span>Payment Status</span>

            <strong className="paid-badge">PAID</strong>
          </div>
        </div>

        {/* CUSTOMER + EVENT */}

        <div className="bill-info-grid">
          <div className="bill-info-box">
            <h3>Customer Details</h3>

            <p>
              <strong>Name:</strong> {customerName}
            </p>

            <p>
              <strong>Email:</strong> {customerEmail}
            </p>

            <p>
              <strong>Phone:</strong> {customerPhone}
            </p>
          </div>

          <div className="bill-info-box">
            <h3>Event Details</h3>

            <p>
              <strong>Event:</strong> {eventName}
            </p>

            <p>
              <strong>Venue:</strong> {venueName}
            </p>

            <p>
              <strong>City:</strong> {venueCity}
            </p>

            <p>
              <strong>Guests:</strong> {booking.guestCount || "-"}
            </p>
          </div>
        </div>

        {/* EVENT DATE */}

        <div className="bill-event-box">
          <div>
            <span>Event Date</span>

            <strong>{booking.eventDate || "-"}</strong>
          </div>

          <div>
            <span>Event Time</span>

            <strong>{booking.eventTime || "-"}</strong>
          </div>
        </div>

        {/* AMOUNT */}

        <div className="bill-amount-section">
          <div className="bill-amount-row">
            <span>Event Management & Services</span>

            <strong>₹{amount.toLocaleString("en-IN")}</strong>
          </div>

          <div className="bill-total-row">
            <span>Total Amount Paid</span>

            <strong>₹{amount.toLocaleString("en-IN")}</strong>
          </div>
        </div>

        {/* QUOTE DETAILS */}

        {booking.quoteDetails && (
          <div className="bill-notes">
            <h3>Quote / Service Details</h3>

            <p>{booking.quoteDetails}</p>
          </div>
        )}

        {/* CUSTOMER REQUIREMENTS */}

        {booking.requirements && (
          <div className="bill-notes">
            <h3>Customer Requirements</h3>

            <p>{booking.requirements}</p>
          </div>
        )}

        {/* FOOTER */}

        <div className="bill-footer">
          <div>
            <strong>Thank you for choosing EventHub!</strong>

            <p>We look forward to making your event memorable.</p>
          </div>

          <div className="bill-contact">
            <p>EventHub Office</p>

            <p>+91 98765 43210</p>

            <p>support@eventhub.com</p>
          </div>
        </div>
      </div>
    </div>
  );
}

export default Bill;
