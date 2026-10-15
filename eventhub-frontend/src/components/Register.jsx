import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import API_BASE_URL from "../api/api";
import "./Register.css";

function Register() {
  const navigate = useNavigate();

  const [fullName, setFullName] = useState("");
  const [email, setEmail] = useState("");
  const [phone, setPhone] = useState("");
  const [password, setPassword] = useState("");

  const [message, setMessage] = useState("");
  const [loading, setLoading] = useState(false);

  const handleRegister = async (event) => {
    event.preventDefault();

    setMessage("");

    if (!fullName || !email || !phone || !password) {
      setMessage("Please fill in all fields.");
      return;
    }

    if (phone.length !== 10) {
      setMessage("Please enter a valid 10-digit phone number.");
      return;
    }

    setLoading(true);

    const userData = {
      fullName: fullName,
      email: email,
      phone: phone,
      password: password,
      role: "CUSTOMER",
    };

    try {
      const response = await fetch(`${API_BASE_URL}/users/register`, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(userData),
      });

      const data = await response.text();

      setLoading(false);

      if (response.ok) {
        setMessage("User Registered Successfully! 🎉");

        setFullName("");
        setEmail("");
        setPhone("");
        setPassword("");

        setTimeout(() => {
          navigate("/login");
        }, 1500);
      } else {
        setMessage(data || "Registration failed. Please try again.");
      }
    } catch (error) {
      console.error("Registration error:", error);
      setLoading(false);
      setMessage("Unable to connect to the server.");
    }
  };

  return (
    <div className="register-page">
      <div className="register-container">
        <div className="register-card">
          <div className="register-header">
            <h1>Create Account</h1>
            <p>Register with EventHub and start planning your event.</p>
          </div>

          <form onSubmit={handleRegister}>
            <div className="register-field">
              <label>Full Name</label>

              <input
                type="text"
                placeholder="Enter your full name"
                value={fullName}
                onChange={(e) => setFullName(e.target.value)}
              />
            </div>

            <div className="register-field">
              <label>Email</label>

              <input
                type="email"
                placeholder="Enter your email"
                value={email}
                onChange={(e) => setEmail(e.target.value)}
              />
            </div>

            <div className="register-field">
              <label>Phone Number</label>

              <input
                type="text"
                placeholder="Enter 10-digit phone number"
                value={phone}
                maxLength="10"
                onChange={(e) => {
                  const value = e.target.value.replace(/\D/g, "");
                  setPhone(value);
                }}
              />
            </div>

            <div className="register-field">
              <label>Password</label>

              <input
                type="password"
                placeholder="Create a password"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
              />
            </div>

            <button
              type="submit"
              className="register-button"
              disabled={loading}
            >
              {loading ? "Registering..." : "Register"}
            </button>
          </form>

          {message && <div className="register-message">{message}</div>}

          <div className="register-login">
            <p>
              Already have an account? <Link to="/login">Login here</Link>
            </p>
          </div>
        </div>
      </div>
    </div>
  );
}

export default Register;
