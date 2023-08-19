import React from "react";
import ReactDOM from "react-dom/client";
import "./assets/fonts/GameofThrones.ttf";
import "./index.css";
import reportWebVitals from "./reportWebVitals";
import TreeStruct from "./compoents/tree";
import Home from "./compoents/home";
import { createBrowserRouter, RouterProvider } from "react-router-dom";
import Index from "./compoents";

const router = createBrowserRouter([
  {
    path: "/",
    element: <Home />,
    children: [
      {
        index: true,
        element: <Index />,
      },
      {
        path: "/:houseName",
        element: <TreeStruct />,
      },
    ],
  },
]);

// const store = createStore(cartReducer);

const root = ReactDOM.createRoot(document.getElementById("root"));
root.render(<RouterProvider router={router} />);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
// reportWebVitals();
