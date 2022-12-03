import './App.css';
import React from 'react';
import NavBar from './components/NavBar';
import Recipes from './components/recipes/Recipes';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import RecipeDetails from './components/recipes/RecipeDetails';
import AddRecipe from './components/recipes/AddRecipe';

function App() {
  return (
    <section>
      <NavBar></NavBar>
      <div className="container mt-3">
        <BrowserRouter>
          <Routes>
            {/* <Route key="recipes" exact path="/" element={<NavBar/>} /> */}
            <Route key="recipes" exact path="/recipes" element={<Recipes/>} />
            <Route key="recipeDetails" exact path="/recipes/:id" element={<RecipeDetails/>} />
            <Route key="addRecipe" exact path="/recipes/add" element={<AddRecipe/>} />
          </Routes>
        </BrowserRouter>
      </div>
    </section>
  );
}

export default App;
