import axios from "axios";
import React, { useContext, useState } from "react";
import { useNavigate } from "react-router-dom";
import { AppContext } from "../App";
import "../styles/create-quiz.css";

function CreateQuiz() {
  const navigate = useNavigate();
  const { setQuizId } = useContext(AppContext);
  const [quiz, setQuiz] = useState({
    category: "",
    noOfQuestions: 0,
    quizTitle: "",
  });

  const handleChange = (event) => {
    const { name, value } = event.target;
    setQuiz({ ...quiz, [name]: value });
  };

  const onSubmit = async (event) => {
    event.preventDefault();
    console.log(quiz);
    try {
      const response = await axios.post(
        "http://localhost:8090/quiz/create",
        quiz
      );
      setQuizId(response.data.id);
      alert("Quiz Created, and quiz id was : " + response.data.id);
      navigate("/play-quiz");
    } catch (err) {
      console.error(err);
    }
  };

  return (
    <div>
      <form onSubmit={onSubmit}>
        <input
          name="quizTitle"
          type="text"
          placeholder="Enter quiz title.."
          onChange={handleChange}
        />
        <input
          onChange={handleChange}
          name="noOfQuestions"
          type="number"
          placeholder="Enter number of questions.."
        />
        <select name="category" onChange={handleChange}>
          <option value="c">C</option>
          <option value="cpp">C++</option>
          <option value="java">Java</option>
          <option value="python">Python</option>
          <option value="html">HTML</option>
          <option value="css">CSS</option>
          <option value="javascript">JavaScript</option>
          <option value="reactjs">ReactJS</option>
          <option value="django">Django</option>
          <option value="dsa">DSA</option>
        </select>
        <input type="submit" value="Create Quiz" />
      </form>
    </div>
  );
}

export default CreateQuiz;
