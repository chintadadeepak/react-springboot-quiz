import React from "react";
import "../styles/navbar.css";
import { Link } from "react-router-dom";
function Navbar() {
  return (
    <div className="navbar-container">
      <Link className="navbar-link" to="/">
        Home
      </Link>
      <Link className="navbar-link" to="/create-quiz">
        Create Quiz
      </Link>
      <Link className="navbar-link" to="/play-quiz">
        Play Quiz
      </Link>
    </div>
  );
}

export default Navbar;
