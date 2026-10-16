import { useEffect, useState } from "react";
import API_BASE_URL from "../api/Api.js";
import "./Review.css";

function Review() {
  const [events, setEvents] = useState([]);
  const [reviews, setReviews] = useState([]);

  const [eventId, setEventId] = useState("");
  const [rating, setRating] = useState(5);
  const [comment, setComment] = useState("");

  const [message, setMessage] = useState("");
  const [loading, setLoading] = useState(true);

  // Fetch events and reviews
  useEffect(() => {
    fetch(`${API_BASE_URL}/events`)
      .then((response) => response.json())
      .then((data) => {
        setEvents(data);
      })
      .catch((error) => {
        console.error("Error fetching events:", error);
      });

    fetch(`${API_BASE_URL}/reviews`)
      .then((response) => response.json())
      .then((data) => {
        setReviews(data);
        setLoading(false);
      })
      .catch((error) => {
        console.error("Error fetching reviews:", error);
        setLoading(false);
      });
  }, []);

  // Submit review
  const handleSubmit = (e) => {
    e.preventDefault();

    if (!eventId) {
      setMessage("Please select an event.");

      return;
    }

    if (!comment.trim()) {
      setMessage("Please enter your comment.");

      return;
    }

    const reviewData = {
      userId: 2,

      eventId: Number(eventId),

      rating: Number(rating),

      comment: comment.trim(),
    };

    fetch(`${API_BASE_URL}/reviews/add`, {
      method: "POST",

      headers: {
        "Content-Type": "application/json",
      },

      body: JSON.stringify(reviewData),
    })
      .then((response) => response.text())

      .then((data) => {
        setMessage(data);

        if (data === "Review Added Successfully") {
          setComment("");

          setRating(5);

          setEventId("");

          // Refresh reviews
          fetch(`${API_BASE_URL}/reviews`)
            .then((response) => response.json())
            .then((data) => {
              setReviews(data);
            });
        }
      })
      .catch((error) => {
        console.error("Error adding review:", error);

        setMessage("Something went wrong. Please try again.");
      });
  };

  return (
    <div className="review-page">
      <div className="review-container">
        <h1 className="review-title">Share Your Experience ⭐</h1>

        <p className="review-subtitle">
          Tell us about your EventHub experience
        </p>

        {/* Review Form */}

        <div className="review-card">
          <h2>Write a Review</h2>

          <form onSubmit={handleSubmit}>
            <label>Select Event</label>

            <select
              value={eventId}
              onChange={(e) => setEventId(e.target.value)}
            >
              <option value="">Choose an event</option>

              {events.map((event) => (
                <option key={event.eventId} value={event.eventId}>
                  {event.eventName}
                </option>
              ))}
            </select>

            <label>Rating</label>

            <div className="rating-stars">
              {[1, 2, 3, 4, 5].map((star) => (
                <button
                  type="button"
                  key={star}
                  className={star <= rating ? "star active" : "star"}
                  onClick={() => setRating(star)}
                >
                  ★
                </button>
              ))}
            </div>

            <label>Your Review</label>

            <textarea
              value={comment}
              onChange={(e) => setComment(e.target.value)}
              placeholder="Write your experience..."
              rows="5"
            />

            <button type="submit" className="review-submit-button">
              Submit Review
            </button>

            {message && <p className="review-message">{message}</p>}
          </form>
        </div>

        {/* Existing Reviews */}

        <div className="reviews-section">
          <h2>Customer Reviews ⭐</h2>

          {loading && <p className="review-empty">Loading reviews...</p>}

          {!loading && reviews.length === 0 && (
            <p className="review-empty">No reviews available yet.</p>
          )}

          <div className="reviews-list">
            {reviews.map((review) => (
              <div className="review-item" key={review.reviewId}>
                <div className="review-item-header">
                  <strong>{review.user?.fullName || "Customer"}</strong>

                  <span>{review.reviewDate}</span>
                </div>

                <div className="review-rating">
                  {"★".repeat(review.rating)}

                  {"☆".repeat(5 - review.rating)}
                </div>

                <p>{review.comment}</p>

                <small>{review.event?.eventName || "Event"}</small>
              </div>
            ))}
          </div>
        </div>
      </div>
    </div>
  );
}

export default Review;
