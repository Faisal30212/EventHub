import weddingImg from "../assets/images/wedding.jpg";
import birthdayImg from "../assets/images/birthday.jpg";
import conferenceImg from "../assets/images/conference.jpg";
import concertImg from "../assets/images/concert.jpg";
import sportsImg from "../assets/images/sports.jpg";
import collegeFestImg from "../assets/images/collegefest.jpg";

const events = [
  {
    id: 1,
    title: "Wedding",
    image: weddingImg,
    description: "Luxury wedding planning with complete event management.",
    price: "₹1,50,000",
    route: "/wedding",
  },
  {
    id: 2,
    title: "Birthday",
    image: birthdayImg,
    description: "Celebrate birthdays with beautiful themes and decorations.",
    price: "₹20,000",
    route: "/birthday",
  },
  {
    id: 3,
    title: "Conference",
    image: conferenceImg,
    description: "Professional conference and seminar management.",
    price: "₹75,000",
    route: "/conference",
  },
  {
    id: 4,
    title: "Concert",
    image: concertImg,
    description: "Music concerts and entertainment events.",
    price: "₹2,00,000",
    route: "/concert",
  },
  {
    id: 5,
    title: "Sports",
    image: sportsImg,
    description: "Sports tournaments and outdoor event planning.",
    price: "₹1,00,000",
    route: "/sports",
  },
  {
    id: 6,
    title: "College Fest",
    image: collegeFestImg,
    description: "College cultural and technical festival management.",
    price: "₹80,000",
    route: "/collegefest",
  },
];

export default events;