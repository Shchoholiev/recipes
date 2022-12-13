import './App.css';
import React from 'react';
import NavBar from './components/NavBar';
import Recipes from './components/recipes/Recipes';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import RecipeDetails from './components/recipes/RecipeDetails';
import AddRecipe from './components/recipes/AddRecipe';
import Categories from './components/categories/Categories';
import AddCategory from './components/categories/AddCategory';

function App() {

  const healthCheck = setInterval(function() {
    fetch(window.location.origin, {
        method: "GET"
    }).then(console.log("Application is running..."));
  }, 1000 * 60 * 5);

  return (
    <BrowserRouter>
      <NavBar></NavBar>
      <div className="container mt-3">
        <Routes>
          <Route key="recipes" exact path="/" element={<Recipes />} />
          <Route key="recipes" exact path="/recipes" element={<Recipes />} />
          <Route key="recipeDetails" exact path="/recipes/:id" element={<RecipeDetails />} />
          <Route key="addRecipe" exact path="/recipes/add" element={<AddRecipe />} />
          <Route key="updateRecipe" exact path="/recipes/edit/:id" element={<AddRecipe />} />
          <Route key="categories" exact path="/categories" element={<Categories />} />
          <Route key="AddCategory" exact path="/categories/add" element={<AddCategory />} />
          <Route key="updateCategory" exact path="/categories/edit/:id" element={<AddCategory />} />
        </Routes>
      </div>
    </BrowserRouter>
  );
}

export default App;
