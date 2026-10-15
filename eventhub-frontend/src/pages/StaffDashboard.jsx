import { useState } from "react";
import "./Login.css";
import { Link, useNavigate } from "react-router-dom";
import { FaEnvelope, FaLock } from "react-icons/fa";
import API_BASE_URL from "../api/api";

function StaffLogin() {
  const navigate = useNavigate();

  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const [message, setMessage] = useState("");
  const [loading, setLoading] = useState(false);

  const handleLogin = async (event) => {
    event.preventDefault();

    setMessage("");

    if (!email.trim() || !password.trim()) {
      setMessage("Please enter email and password.");
      return;
    }

    setLoading(true);

    try {
      const response = await fetch(`${API_BASE_URL}/users/login`, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          email: email.trim(),
          password: password,
        }),
      });

      let data = null;

      try {
        data = await response.json();
      } catch (error) {
        data = null;
      }

      if (!response.ok || !data) {
        setMessage("Invalid manager email or password.");
        return;
      }

      const role = String(data.role || "").toUpperCase();

      // Only managers can use this login page.
      if (role !== "CITY_MANAGER" && role !== "HEAD_MANAGER") {
        setMessage("This account is not a manager account.");
        return;
      }

      // Save manager details for the dashboard.
      localStorage.setItem("user", JSON.stringify(data));
      localStorage.setItem("eventhubUser", JSON.stringify(data));

      // All managers use the same dashboard component.
      // The dashboard decides what the manager can see.
      navigate("/manager-dashboard");
    } catch (error) {
      console.error("Manager login error:", error);
      setMessage(
        "Unable to connect to the server. Please make sure Spring Boot is running.",
      );
    } finally {
      setLoading(false);
    }
  };

  return (
    <section className="login-page">
      <div className="container">
        <div className="row justify-content-center">
          <div className="col-lg-5 col-md-8">
            <div className="login-card">
              <div className="login-heading">
                <span className="login-label">EVENTHUB</span>

                <h2>Manager Login</h2>

                <p>Login to manage event requests for your assigned city.</p>
              </div>

              <form onSubmit={handleLogin}>
                <div className="input-group-custom">
                  <span>
                    <FaEnvelope />
                  </span>

                  <input
                    type="email"
                    value={email}
                    onChange={(event) => setEmail(event.target.value)}
                    placeholder="Manager Email Address"
                    className="form-control"
                    autoComplete="email"
                  />
                </div>

                <div className="input-group-custom">
                  <span>
                    <FaLock />
                  </span>

                  <input
                    type="password"
                    value={password}
                    onChange={(event) => setPassword(event.target.value)}
                    placeholder="Password"
                    className="form-control"
                    autoComplete="current-password"
                  />
                </div>

                <button
                  type="submit"
                  className="btn btn-warning w-100 login-btn"
                  disabled={loading}
                >
                  {loading ? "Logging in..." : "Manager Login"}
                </button>
              </form>

              {message && (
                <div
                  style={{
                    marginTop: "20px",
                    textAlign: "center",
                    color: "red",
                    fontWeight: "600",
                  }}
                >
                  {message}
                </div>
              )}

              <div className="register-link">
                Customer Login?
                <Link to="/login">Login</Link>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
  );
}

export default StaffLogin;
