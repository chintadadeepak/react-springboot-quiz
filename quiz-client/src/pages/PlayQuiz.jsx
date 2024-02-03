import axios from "axios";
import React, { useContext, useState } from "react";
import { AppContext } from "../App";
import "../styles/play-quiz.css";

function PlayQuiz() {
  const [showQuiz, setShowQuiz] = useState(false);
  return <div>{showQuiz ? <Quiz /> : <Form setShowQuiz={setShowQuiz} />}</div>;
}

const Form = (props) => {
  const { quizId, setQuizQuestions } = useContext(AppContext);
  const [id, setId] = useState(quizId);
  const onSubmit = async (event) => {
    event.preventDefault();
    try {
      const response = await axios.get(
        `http://localhost:8090/quiz/getQuiz/${id}`
      );
      setQuizQuestions(response.data);
    } catch (err) {
      console.error(err);
    }
    props.setShowQuiz(true);
  };
  return (
    <form onSubmit={onSubmit}>
      <input
        type="text"
        value={id}
        onChange={(event) => {
          setId(event.target.value);
        }}
      />
      <input type="submit" value="Get Quiz By ID" />
    </form>
  );
};

const Quiz = () => {
  const { quizQuestions } = useContext(AppContext);
  const [responses, setResponses] = useState([]);

  const handleChange = (value, index) => {
    const existingResponseIndex = responses.findIndex(
      (response) => response.id === index
    );

    if (existingResponseIndex !== -1) {
      setResponses((prevResponses) => [
        ...prevResponses.slice(0, existingResponseIndex),
        { id: index, answer: value },
        ...prevResponses.slice(existingResponseIndex + 1),
      ]);
    } else {
      setResponses((prevResponses) => [
        ...prevResponses,
        { id: index, answer: value },
      ]);
    }
    console.log(responses);
  };

  const getScore = async () => {
    const response = await axios.post(
      "http://localhost:8090/quiz/submit",
      responses
    );
    alert("Your score : " + response.data);
  };

  return (
    <div className="play-quiz">
      <h2 className="play-quiz_title">Play Quiz</h2>
      <div>
        {quizQuestions.map((question) => {
          return (
            <div className="play-quiz_question-card" key={question.id}>
              <h3 className="play-quiz_question-title">
                Question Title : {question.questionTitle}
              </h3>
              <p className="play-quiz_option">1. {question.option1}</p>
              <p className="play-quiz_option">2. {question.option2}</p>
              <p className="play-quiz_option">3. {question.option3}</p>
              <p className="play-quiz_option">4. {question.option4}</p>
              <input
                className="play-quiz_answer-input"
                type="text"
                placeholder="Type your answer here.."
                onChange={(event) =>
                  handleChange(event.target.value, question.id)
                }
              />
            </div>
          );
        })}
      </div>
      <button className="play-quiz_get-score-button" onClick={getScore}>
        Get Score
      </button>
    </div>
  );
};

export default PlayQuiz;
