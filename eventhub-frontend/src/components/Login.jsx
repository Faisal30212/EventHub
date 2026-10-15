import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { FaEnvelope, FaLock, FaEye, FaEyeSlash } from "react-icons/fa";
import "./Login.css";

const API_BASE_URL = "http://localhost:8080";

function Login() {
  const navigate = useNavigate();

  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const [showPassword, setShowPassword] = useState(false);

  const [message, setMessage] = useState("");
  const [loading, setLoading] = useState(false);

  const handleLogin = async (event) => {
    event.preventDefault();

    setMessage("");

    if (!email.trim()) {
      setMessage("Please enter your email address.");
      return;
    }

    if (!password.trim()) {
      setMessage("Please enter your password.");
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
        setMessage("Invalid email or password.");
        return;
      }

      const role = String(data.role || "").toUpperCase();

      // Customer login page should only be used by customers.
      if (role !== "CUSTOMER") {
        setMessage("This is not a customer account. Please use Manager Login.");
        return;
      }

      // Save the logged-in customer.
      localStorage.setItem("user", JSON.stringify(data));
      localStorage.setItem("eventhubUser", JSON.stringify(data));

      // Customer enters the home page after login.
      navigate("/");
    } catch (error) {
      console.error("Login error:", error);

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
              {/* =========================
                  HEADER
              ========================== */}

              <div className="login-heading">
                <span className="login-label">EVENTHUB</span>

                <h2>Customer Login</h2>

                <p>Login to plan and manage your events.</p>
              </div>

              {/* =========================
                  LOGIN FORM
              ========================== */}

              <form onSubmit={handleLogin}>
                {/* EMAIL */}

                <div className="input-group-custom">
                  <span>
                    <FaEnvelope />
                  </span>

                  <input
                    type="email"
                    value={email}
                    onChange={(event) => setEmail(event.target.value)}
                    placeholder="Email Address"
                    className="form-control"
                    autoComplete="email"
                  />
                </div>

                {/* PASSWORD */}

                <div className="input-group-custom password-input-group">
                  <span>
                    <FaLock />
                  </span>

                  <input
                    type={showPassword ? "text" : "password"}
                    value={password}
                    onChange={(event) => setPassword(event.target.value)}
                    placeholder="Password"
                    className="form-control"
                    autoComplete="current-password"
                  />

                  {/* SHOW / HIDE PASSWORD */}

                  <button
                    type="button"
                    className="password-toggle"
                    onClick={() => setShowPassword(!showPassword)}
                    aria-label={
                      showPassword ? "Hide password" : "Show password"
                    }
                  >
                    {showPassword ? <FaEyeSlash /> : <FaEye />}
                  </button>
                </div>

                {/* LOGIN BUTTON */}

                <button
                  type="submit"
                  className="btn btn-warning w-100 login-btn"
                  disabled={loading}
                >
                  {loading ? "Logging in..." : "Login"}
                </button>
              </form>

              {/* =========================
                  ERROR MESSAGE
              ========================== */}

              {message && <div className="login-error-message">{message}</div>}

              {/* =========================
                  REGISTER
              ========================== */}

              <div className="register-link">
                Don't have an account?
                <Link to="/register">Register</Link>
              </div>

              {/* =========================
                  MANAGER LOGIN
              ========================== */}

              <div className="register-link">
                Are you a manager?
                <Link to="/staff-login">Manager Login</Link>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
  );
}

export default Login;
