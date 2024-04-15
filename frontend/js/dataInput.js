export class DataInput {

    constructor(element) {
        const countryMap = {
            "Afeganistão": "Afghanistan",
            "África do Sul": "South Africa",
            "Albânia": "Albania",
            "Alemanha": "Germany",
            "Andorra": "Andorra",
            "Angola": "Angola",
            "Antígua e Barbuda": "Antigua and Barbuda",
            "Arábia Saudita": "Saudi Arabia",
            "Argélia": "Algeria",
            "Argentina": "Argentina",
            "Armênia": "Armenia",
            "Austrália": "Australia",
            "Áustria": "Austria",
            "Azerbaijão": "Azerbaijan",
            "Bahamas": "Bahamas",
            "Bangladesh": "Bangladesh",
            "Barbados": "Barbados",
            "Barém": "Bahrain",
            "Bélgica": "Belgium",
            "Belize": "Belize",
            "Benin": "Benin",
            "Bielorrússia": "Belarus",
            "Birmânia": "Burma",
            "Bolívia": "Bolivia",
            "Bósnia e Herzegovina": "Bosnia and Herzegovina",
            "Botsuana": "Botswana",
            "Brasil": "Brazil",
            "Brunei": "Brunei",
            "Bulgária": "Bulgaria",
            "Burkina Faso": "Burkina Faso",
            "Burundi": "Burundi",
            "Butão": "Bhutan",
            "Cabo Verde": "Cabo Verde",
            "Camarões": "Cameroon",
            "Camboja": "Cambodia",
            "Canadá": "Canada",
            "Catar": "Qatar",
            "Cazaquistão": "Kazakhstan",
            "Chade": "Chad",
            "Chile": "Chile",
            "China": "China",
            "Chipre": "Cyprus",
            "Colômbia": "Colombia",
            "Comores": "Comoros",
            "Congo (Brazzaville)": "Congo (Brazzaville)",
            "Congo (Kinshasa)": "Congo (Kinshasa)",
            "Coreia do Norte": "Korea North",
            "Coreia do Sul": "Korea South",
            "Costa do Marfim": "Cote d'Ivoire",
            "Costa Rica": "Costa Rica",
            "Croácia": "Croatia",
            "Cuba": "Cuba",
            "Dinamarca": "Denmark",
            "Dominica": "Dominica",
            "Egito": "Egypt",
            "El Salvador": "El Salvador",
            "Emirados Árabes Unidos": "United Arab Emirates",
            "Equador": "Ecuador",
            "Eritreia": "Eritrea",
            "Eslováquia": "Slovakia",
            "Eslovênia": "Slovenia",
            "Espanha": "Spain",
            "Estados Unidos (EUA)": "US",
            "Estônia": "Estonia",
            "Etiópia": "Ethiopia",
            "Fiji": "Fiji",
            "Filipinas": "Philippines",
            "Finlândia": "Finland",
            "França": "France",
            "Gabão": "Gabon",
            "Gâmbia": "Gambia",
            "Gana": "Ghana",
            "Geórgia": "Georgia",
            "Granada": "Grenada",
            "Grécia": "Greece",
            "Guatemala": "Guatemala",
            "Guiana": "Guyana",
            "Guiné": "Guinea",
            "Guiné Equatorial": "Equatorial Guinea",
            "Guiné-Bissau": "Guinea-Bissau",
            "Haiti": "Haiti",
            "Holanda": "Netherlands",
            "Honduras": "Honduras",
            "Hungria": "Hungary",
            "Iêmen": "Yemen",
            "Ilhas Marshall": "Marshall Islands",
            "Índia": "India",
            "Indonésia": "Indonesia",
            "Irã": "Iran",
            "Iraque": "Iraq",
            "Irlanda": "Ireland",
            "Islândia": "Iceland",
            "Israel": "Israel",
            "Itália": "Italy",
            "Jamaica": "Jamaica",
            "Japão": "Japan",
            "Jordânia": "Jordan",
            "Kiribati": "Kiribati",
            "Kosovo": "Kosovo",
            "Kuwait": "Kuwait",
            "Laos": "Laos",
            "Lesoto": "Lesotho",
            "Letônia": "Latvia",
            "Líbano": "Lebanon",
            "Libéria": "Liberia",
            "Líbia": "Libya",
            "Liechtenstein": "Liechtenstein",
            "Lituânia": "Lithuania",
            "Luxemburgo": "Luxembourg",
            "Madagáscar": "Madagascar",
            "Malásia": "Malaysia",
            "Malawi": "Malawi",
            "Maldivas": "Maldives",
            "Mali": "Mali",
            "Malta": "Malta",
            "Marrocos": "Morocco",
            "Maurício": "Mauritius",
            "Mauritânia": "Mauritania",
            "México": "Mexico",
            "Micronésia": "Micronesia",
            "Moçambique": "Mozambique",
            "Moldávia": "Moldova",
            "Mônaco": "Monaco",
            "Mongólia": "Mongolia",
            "Montenegro": "Montenegro",
            "Namíbia": "Namibia",
            "Nauru": "Nauru",
            "Nepal": "Nepal",
            "Nicarágua": "Nicaragua",
            "Níger": "Niger",
            "Nigéria": "Nigeria",
            "Noruega": "Norway",
            "Nova Zelândia": "New Zealand",
            "Omã": "Oman",
            "Palau": "Palau",
            "Panamá": "Panama",
            "Papua-Nova Guiné": "Papua New Guinea",
            "Paquistão": "Pakistan",
            "Paraguai": "Paraguay",
            "Peru": "Peru",
            "Polônia": "Poland",
            "Portugal": "Portugal",
            "Quênia": "Kenya",
            "Quirguistão": "Kyrgyzstan",
            "Reino Unido": "United Kingdom",
            "República Centro-Africana": "Central African Republic",
            "República Checa": "Czechia",
            "República Democrática do Congo": "Congo (Kinshasa)",
            "República Dominicana": "Dominican Republic",
            "Romênia": "Romania",
            "Ruanda": "Rwanda",
            "Rússia": "Russia",
            "Salomão": "Solomon Islands",
            "Salvador": "El Salvador",
            "Samoa": "Samoa",
            "Santa Lúcia": "Saint Lucia",
            "São Cristóvão e Nevis": "Saint Kitts and Nevis",
            "São Marinho": "San Marino",
            "São Tomé e Príncipe": "Sao Tome and Principe",
            "São Vicente e Granadinas": "Saint Vincent and the Grenadines",
            "Seicheles": "Seychelles",
            "Senegal": "Senegal",
            "Serra Leoa": "Sierra Leone",
            "Sérvia": "Serbia",
            "Singapura": "Singapore",
            "Síria": "Syria",
            "Somália": "Somalia",
            "Sri Lanka": "Sri Lanka",
            "Suazilândia": "Eswatini",
            "Sudão": "Sudan",
            "Sudão do Sul": "South Sudan",
            "Suécia": "Sweden",
            "Suíça": "Switzerland",
            "Suriname": "Suriname",
            "Tadjiquistão": "Tajikistan",
            "Tailândia": "Thailand",
            "Tanzânia": "Tanzania",
            "Timor-Leste": "Timor-Leste",
            "Togo": "Togo",
            "Tonga": "Tonga",
            "Trinidad e Tobago": "Trinidad and Tobago",
            "Tunísia": "Tunisia",
            "Turcomenistão": "Turkmenistan",
            "Turquia": "Turkey",
            "Tuvalu": "Tuvalu",
            "Ucrânia": "Ukraine",
            "Uganda": "Uganda",
            "Uruguai": "Uruguay",
            "Uzbequistão": "Uzbekistan",
            "Vanuatu": "Vanuatu",
            "Vaticano": "Holy See",
            "Venezuela": "Venezuela",
            "Vietnã": "Vietnam",
            "Zâmbia": "Zambia",
            "Zimbábue": "Zimbabwe"
        };
        

        const countries1Input = document.getElementById('country1');
        const countries2Input = document.getElementById('country2');
        const countries1Datalist = document.createElement('datalist');
        const countries2Datalist = document.createElement('datalist');

        countries1Datalist.id = 'countries1';
        countries2Datalist.id = 'countries2';

        // Adiciona opções para o datalist
        for (const [portugueseName, englishName] of Object.entries(countryMap)) {
            const option1 = document.createElement('option');
            const option2 = document.createElement('option');
            option1.value = portugueseName;
            option2.value = portugueseName;
            countries1Datalist.appendChild(option1);
            countries2Datalist.appendChild(option2);
        }

        countries1Input.appendChild(countries1Datalist);
        countries2Input.appendChild(countries2Datalist);
    }
}
