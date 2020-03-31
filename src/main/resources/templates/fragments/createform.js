document.getElementById("birthday").setAttribute("max", minAge())

function minAge() {
    let today = new Date();
    let dd = today.getDate();
    let mm = today.getMonth() + 1;
    let yyyy = today.getFullYear() - 18; //minimum van 18 jaar.
    if (dd < 10) {
        dd = '0' + dd;
    }

    if (mm < 10) {
        mm = '0' + mm;
    }

    return yyyy + "-" + mm + "-" + dd;
}


let inputValue = document.getElementById("socialSecurityNumber");
inputValue.addEventListener("input", function () {
    let ssn = inputValue.value;

    if (ssn.length == 9) {

        let URL = `http://localhost:8080/api/ssn_check/${ssn}`; // API URL voor de request

        fetch(URL) // API URL
            .then((response) => {
                if (!response.ok) {// Methode is een boolean en geeft true bij een een status 200-299
                    throw new Error("Error with respons"); // gooit een error met een message
                }
                return response.json(); // Zet de JSON promise om in een javascript object
            })
            .then((data) => { // promise => Javascript object met data.
                console.log(data)
                if (data == true) {
                    document.querySelector("#ssnError").textContent = "Account met BSN is al bekend."
                }

                // template string en zet hem in de html
            })
            .catch((error) => {
                console.log(error); // handelt de errors af en drukt ze af in de console
            })

    };
});
