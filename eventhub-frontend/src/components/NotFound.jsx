import "./NotFound.css";
import { Link } from "react-router-dom";
import { FaHome, FaArrowLeft } from "react-icons/fa";

function NotFound() {
  return (
    <section className="notfound">
      <div className="container">
        <div className="notfound-content">
          <h1>404</h1>

          <h2>Oops! Page Not Found</h2>

          <p>
            The page you are looking for doesn't exist or has been moved. Let's
            get you back to the EventHub homepage.
          </p>

          <div className="notfound-buttons">
            <Link to="/" className="btn btn-primary home-btn">
              <FaHome /> Home
            </Link>

            <button
              className="btn btn-outline-primary back-btn"
              onClick={() => window.history.back()}
            >
              <FaArrowLeft /> Go Back
            </button>
          </div>
        </div>
      </div>
    </section>
  );
}

export default NotFound;
