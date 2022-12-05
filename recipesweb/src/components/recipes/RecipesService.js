const RecipesService = {

    baseUrl: "http://localhost:8080/api/recipes",

    getPage: function(pageNumber) {
        return fetch(`${this.baseUrl}/page/${pageNumber}`, {
            method: "GET"
        });
    },

    getRecipe: function(id) {
        return fetch(`${this.baseUrl}/${id}`, {
            method: "GET"
        });
    },

    add: function(data) {
        return fetch(`${this.baseUrl}`, {
            method: "POST",
            headers: {'Content-Type':'application/json'},
            body: JSON.stringify(data)
        });
    },

    update: function(id, data) {
        return fetch(`${this.baseUrl}/${id}`, {
            method: "PUT",
            headers: {'Content-Type':'application/json'},
            body: JSON.stringify(data)
        });
    },

    delete: function(id) {
        return fetch(`${this.baseUrl}/${id}`, {
            method: "DELETE"
        });
    },
}

export default RecipesService