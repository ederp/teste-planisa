import { DataInput }  from './dataInput.js';

const selectPaises1 = document.getElementById("country1");
const selectPaises2 = document.getElementById("country2");
new DataInput(selectPaises1);
new DataInput(selectPaises2);

const form = document.getElementById('totalCasesDeathsForm');
const inputs = form.querySelectorAll('.country-input, #fromDate, #dataType');
const getDataBtn = document.getElementById('getDataBtn');

inputs.forEach(input => {
    input.addEventListener('change', () => {
        const allFilled = Array.from(inputs).every(input => input.value.trim() !== '');
        getDataBtn.disabled = !allFilled;
    });
});

// Adicione um evento de escuta ao seletor de tipo de dados
const dataTypeSelect = document.getElementById('dataType');
const toDateInput = document.getElementById('toDate');

dataTypeSelect.addEventListener('change', () => {
    const selectedOption = dataTypeSelect.value;
    
    // Verifica se as duas últimas opções foram selecionadas
    if (selectedOption === 'newCases' || selectedOption === 'newDeaths') {
        // Desativa o segundo input de data
        toDateInput.disabled = true;
    } else {
        // Caso contrário, habilita o segundo input de data
        toDateInput.disabled = false;
    }
});


// Adicione um evento de escuta ao formulário
form.addEventListener('submit', async (event) => {
    event.preventDefault(); // Impede o comportamento padrão de recarregar a página

    // Mostra o indicador de carregamento
    const loadingIndicator = document.getElementById('loadingIndicator');
    loadingIndicator.style.display = 'block';
    
    // Obtém os valores dos campos de entrada
    const country1 = document.getElementById('listCountry1').value;
    const country2 = document.getElementById('listCountry2').value;
    const fromDate = document.getElementById('fromDate').value;
    const toDate = document.getElementById('toDate').value;
    const dataType = document.getElementById('dataType').value;

    function queryDataType(baseUrl, value){
        const url = {
            totalCases: `${baseUrl}cases/dates/${country1}/${country2}?from=${fromDate}&to=${toDate}`,
            totalDeaths: `${baseUrl}deaths/dates/${country1}/${country2}?from=${fromDate}&to=${toDate}`,
            newCases: `${baseUrl}cases/date/${country1}/${country2}?from=${fromDate}`,
            newDeaths: `${baseUrl}deaths/date/${country1}/${country2}?from=${fromDate}`
        }

        return url[value];
    }
    
    // Constrói a URL para a solicitação
    const baseUrl = 'http://localhost:8080/benchmark/';
    const url = queryDataType(baseUrl, dataType);
    
    try {
        // Envia a solicitação ao backend
        const response = await fetch(url);
        
        // Verifica se a solicitação foi bem-sucedida
        if (!response.ok) {
            throw new Error('Erro ao obter dados do servidor.');
        }
        
        // Obtém os dados da resposta
        const data = await response.json();
        
        // Exibe os dados na tabela
        displayData(data);
    } catch (error) {
        console.error('Erro ao processar a solicitação:', error);
    }
});

// Função para exibir os dados na tabela
function displayData(data) {
    // Oculta o indicador de carregamento
    const loadingIndicator = document.getElementById('loadingIndicator');
    loadingIndicator.style.display = 'none';
    // Limpa o conteúdo da div 'result'
    const resultDiv = document.getElementById('result');
    resultDiv.innerHTML = '';

    const dataType = document.getElementById('dataType').value;
    
    // Cria uma tabela
    const table = document.createElement('table');

    // Cria uma linha de cabeçalho
    const headerRow = document.createElement('tr');

    let headers = [];
    if (dataType === 'totalCases' || dataType === 'newCases') {
        headers = ['País', 'Data', 'Total de Casos', 'Novos Casos'];
    } else if (dataType === 'totalDeaths' || dataType === 'newDeaths') {
        headers = ['País', 'Data', 'Total de Mortes', 'Novas Mortes'];
    }

    headers.forEach(headerText => {
        const header = document.createElement('th');
        header.textContent = headerText;
        headerRow.appendChild(header);
    });
    table.appendChild(headerRow);
    
    // Adiciona uma linha para cada país
    data.forEach(countryData => {
        const row = document.createElement('tr');
        let columns = [];
        if (dataType === 'totalCases' || dataType === 'newCases') {
            columns = [countryData.country, countryData.date, countryData.totalCases, countryData.newCases];
        } else if (dataType === 'totalDeaths' || dataType === 'newDeaths') {
            columns = [countryData.country, countryData.date, countryData.totalDeaths, countryData.newDeaths];
        }
        columns.forEach(columnText => {
            const cell = document.createElement('td');
            cell.textContent = columnText;
            row.appendChild(cell);
        });
        table.appendChild(row);
    });
    
    // Adiciona a tabela à div 'result'
    resultDiv.appendChild(table);
}

// Adicione um evento de escuta ao botão "Limpar"
const clearDataBtn = document.getElementById('clearDataBtn');
clearDataBtn.addEventListener('click', () => {
    // Limpa os campos de entrada
    document.getElementById('listCountry1').value = '';
    document.getElementById('listCountry2').value = '';
    document.getElementById('fromDate').value = '';
    document.getElementById('toDate').value = '';
    document.getElementById('dataType').value = '';
    
    // Remove as tabelas de dados
    const resultDiv = document.getElementById('result');
    resultDiv.innerHTML = '';
});

