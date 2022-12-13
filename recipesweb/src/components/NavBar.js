import React from 'react';
import { Link } from 'react-router-dom';

export default function NavBar() {
    return (
        <nav className="navbar navbar-expand-lg navbar-light bg-light">
            <div className="container-fluid">
                <Link className="navbar-brand" to="#">Recipes App</Link>
                <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span className="navbar-toggler-icon"></span>
                </button>
                <div className="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul className="navbar-nav me-auto mb-2 mb-lg-0">
                        <li className="nav-item dropdown">
                            <Link className="nav-link dropdown-toggle" id="recipesDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                Recipes
                            </Link>
                            <ul className="dropdown-menu" aria-labelledby="recipesDropdown">
                                <li><Link className="dropdown-item" to="/recipes">All Recipes</Link></li>
                                <li><Link className="dropdown-item" to="/recipes/add">Add Recipe</Link></li>
                            </ul>
                        </li>
                        <li className="nav-item dropdown">
                            <Link className="nav-link dropdown-toggle" id="categoriesDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                Categories
                            </Link>
                            <ul className="dropdown-menu" aria-labelledby="categoriesDropdown">
                                <li><Link className="dropdown-item" to="/categories">All Categories</Link></li>
                                <li><Link className="dropdown-item" to="/categories/add">Add Category</Link></li>
                            </ul>
                        </li>
                    </ul>
                    {/* <form className="d-flex">
                        <input className="form-control me-2" type="search" placeholder="Search" aria-label="Search" />
                        <button className="btn btn-outline-success" type="submit">Search</button>
                    </form> */}
                </div>
            </div>
        </nav>
    );
}