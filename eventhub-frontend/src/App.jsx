import { BrowserRouter, Routes, Route } from "react-router-dom";

// Main Pages
import Home from "./pages/Home";
import Venue from "./pages/Venue";
import VenueDetails from "./pages/VenueDetails";
import Booking from "./pages/Booking";
import Review from "./pages/Review";
import ManagerDashboard from "./pages/ManagerDashboard";
import MyBookings from "./pages/MyBookings";
import Bill from "./pages/Bill";
import PreviousEvents from "./pages/PreviousEvents";

// Existing homepage event section
import FeaturedEvents from "./components/FeaturedEvents";

// Login
import Login from "./components/Login";
import Register from "./components/Register";
import StaffLogin from "./components/StaffLogin";

// Event pages
import Wedding from "./pages/Wedding";
import Birthday from "./pages/Birthday";
import Conference from "./pages/Conference";
import Concert from "./pages/Concert";
import Sports from "./pages/Sports";
import CollegeFest from "./pages/CollegeFest";

// Event detail pages
import WeddingDetails from "./pages/WeddingDetails";
import BirthdayDetails from "./pages/BirthdayDetails";
import ConferenceDetails from "./pages/ConferenceDetails";
import ConcertDetails from "./pages/ConcertDetails";
import SportsDetails from "./pages/SportsDetails";
import CollegeFestDetails from "./pages/CollegeFestDetails";

function App() {
  return (
    <BrowserRouter>
      <Routes>
        {/* ================= HOME ================= */}

        <Route path="/" element={<Home />} />
        <Route path="/previous-events" element={<PreviousEvents />} />

        {/* ================= LOGIN ================= */}

        <Route path="/login" element={<Login />} />

        <Route path="/register" element={<Register />} />

        <Route path="/staff-login" element={<StaffLogin />} />

        {/* ================= MANAGER ================= */}

        <Route path="/manager-dashboard" element={<ManagerDashboard />} />

        {/* ================= EVENTS ================= */}

        <Route path="/events" element={<FeaturedEvents />} />

        {/* ================= EVENT TYPES ================= */}

        <Route path="/wedding" element={<Wedding />} />

        <Route path="/birthday" element={<Birthday />} />

        <Route path="/conference" element={<Conference />} />

        <Route path="/concert" element={<Concert />} />

        <Route path="/sports" element={<Sports />} />

        <Route path="/collegefest" element={<CollegeFest />} />

        {/* ================= EVENT DETAILS ================= */}

        <Route path="/wedding/:id" element={<WeddingDetails />} />

        <Route path="/birthday/:id" element={<BirthdayDetails />} />

        <Route path="/conference/:id" element={<ConferenceDetails />} />

        <Route path="/concert/:id" element={<ConcertDetails />} />

        <Route path="/sports/:id" element={<SportsDetails />} />

        <Route path="/collegefest/:id" element={<CollegeFestDetails />} />

        {/* ================= EVENT PLANNING ================= */}

        <Route path="/bookings" element={<Booking />} />

        <Route path="/bookings/:eventId" element={<Booking />} />

        {/* ================= CUSTOMER BOOKINGS ================= */}

        <Route path="/my-bookings" element={<MyBookings />} />

        {/* ================= BILL ================= */}

        <Route path="/bill/:bookingId" element={<Bill />} />

        {/* ================= VENUES ================= */}

        <Route path="/venues" element={<Venue />} />

        <Route path="/venues/:id" element={<VenueDetails />} />

        {/* ================= REVIEWS ================= */}

        <Route path="/reviews" element={<Review />} />

        {/* ================= PAGE NOT FOUND ================= */}

        <Route
          path="*"
          element={
            <div
              style={{
                minHeight: "100vh",
                display: "flex",
                alignItems: "center",
                justifyContent: "center",
                background: "#07111f",
                color: "white",
                fontSize: "28px",
              }}
            >
              Page Not Found
            </div>
          }
        />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
