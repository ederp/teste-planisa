import { DataInput }  from './dataInput.js';

const selectPaises1 = document.getElementById("country1");
const selectPaises2 = document.getElementById("country2");
new DataInput(selectPaises1);
new DataInput(selectPaises2);

const form = document.getElementById('totalCasesDeathsForm');
const inputs = form.querySelectorAll('.country-input, #fromDate, #toDate, #dataType');
const getDataBtn = document.getElementById('getDataBtn');

inputs.forEach(input => {
    input.addEventListener('change', () => {
        const allFilled = Array.from(inputs).every(input => input.value.trim() !== '');
        getDataBtn.disabled = !allFilled;
    });
});