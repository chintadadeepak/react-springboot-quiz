import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import "./App.css";
import Home from "./pages/Home";
import CreateQuiz from "./pages/CreateQuiz";
import PlayQuiz from "./pages/PlayQuiz";
import Navbar from "./components/Navbar";
import { createContext, useState } from "react";

export const AppContext = createContext();

function App() {
  const [quizId, setQuizId] = useState(0);
  const [quizQuestions, setQuizQuestions] = useState([]);
  return (
    <div>
      <AppContext.Provider
        value={{ quizId, setQuizId, quizQuestions, setQuizQuestions }}
      >
        <Router>
          <Navbar />
          <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/create-quiz" element={<CreateQuiz />} />
            <Route path="/play-quiz" element={<PlayQuiz />} />
            <Route
              path="*"
              element={
                <h1 style={{ color: "red", textAlign: "center" }}>
                  PAGE NOT FOUND
                </h1>
              }
            />
          </Routes>
        </Router>
      </AppContext.Provider>
    </div>
  );
}

export default App;
