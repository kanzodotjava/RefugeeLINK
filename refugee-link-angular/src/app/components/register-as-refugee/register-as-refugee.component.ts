import { Component } from '@angular/core';
import { OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ApiService } from '../../services/ApiService/api.service';
import { Router } from '@angular/router';

enum Language {
  MANDARIN = 'MANDARIN',
  BENGALI = 'BENGALI',
  TURKISH = 'TURKISH',
  GERMAN = 'GERMAN',
  ARABIC = 'ARABIC',
  ENGLISH = 'ENGLISH',
  JAPANESE = 'JAPANESE',
  HINDI = 'HINDI',
  RUSSIAN = 'RUSSIAN',
  SPANISH = 'SPANISH',
  PORTUGUESE = 'PORTUGUESE',
  FRENCH = 'FRENCH',
  ITALIAN = 'ITALIAN',
  KOREAN = 'KOREAN',
  UKRAINIAN = 'UKRAINIAN',
}

enum Country {
  USA = 'UNITED_STATES_OF_AMERICA',
  AFGHANISTAN = 'AFGHANISTAN',
  ALBANIA = 'ALBANIA',
  ALGERIA = 'ALGERIA',
  ANDORRA = 'ANDORRA',
  ANGOLA = 'ANGOLA',
  ANTIGUA_DEPS = 'ANTIGUA_&_DEPS',
  ARGENTINA = 'ARGENTINA',
  ARMENIA = 'ARMENIA',
  AUSTRALIA = 'AUSTRALIA',
  AUSTRIA = 'AUSTRIA',
  AZERBAIJAN = 'AZERBAIJAN',
  BAHAMAS = 'BAHAMAS',
  BAHRAIN = 'BAHRAIN',
  BANGLADESH = 'BANGLADESH',
  BARBADOS = 'BARBADOS',
  BELARUS = 'BELARUS',
  BELGIUM = 'BELGIUM',
  BELIZE = 'BELIZE',
  BENIN = 'BENIN',
  BHUTAN = 'BHUTAN',
  BOLIVIA = 'BOLIVIA',
  BOSNIA_HERZEGOVINA = 'BOSNIA_HERZEGOVINA',
  BOTSWANA = 'BOTSWANA',
  BRAZIL = 'BRAZIL',
  BRUNEI = 'BRUNEI',
  BULGARIA = 'BULGARIA',
  BURKINA = 'BURKINA',
  BURMA = 'BURMA',
  BURUNDI = 'BURUNDI',
  CAMBODIA = 'CAMBODIA',
  CAMEROON = 'CAMEROON',
  CANADA = 'CANADA',
  CAPE_VERDE = 'CAPE_VERDE',
  CENTRAL_AFRICAN_REP = 'CENTRAL_AFRICAN_REP',
  CHAD = 'CHAD',
  CHILE = 'CHILE',
  CHINA = 'REPUBLIC_OF_CHINA',
  REPUBLIC_OF_CHINA = 'REPUBLIC_OF_CHINA',
  COLOMBIA = 'COLOMBIA',
  COMOROS = 'COMOROS',
  DEMOCRATIC_REPUBLIC_OF_THE_CONGO = 'DEMOCRATIC_REPUBLIC_OF_THE_CONGO',
  REPUBLIC_OF_THE_CONGO = 'REPUBLIC_OF_THE_CONGO',
  COSTA_RICA = 'COSTA_RICA',
  CROATIA = 'CROATIA',
  CUBA = 'CUBA',
  CYPRUS = 'CYPRUS',
  CZECH_REPUBLIC = 'CZECH_REPUBLIC',
  DANZIG = 'DANZIG',
  DENMARK = 'DENMARK',
  DJIBOUTI = 'DJIBOUTI',
  DOMINICA = 'DOMINICA',
  DOMINICAN_REPUBLIC = 'DOMINICAN_REPUBLIC',
  EAST_TIMOR = 'EAST_TIMOR',
  ECUADOR = 'ECUADOR',
  EGYPT = 'EGYPT',
  EL_SALVADOR = 'EL_SALVADOR',
  EQUATORIAL_GUINEA = 'EQUATORIAL_GUINEA',
  ERITREA = 'ERITREA',
  ESTONIA = 'ESTONIA',
  ETHIOPIA = 'ETHIOPIA',
  FIJI = 'FIJI',
  FINLAND = 'FINLAND',
  FRANCE = 'FRANCE',
  GABON = 'GABON',
  GAZA_STRIP = 'GAZA_STRIP',
  THE_GAMBIA = 'THE_GAMBIA',
  GEORGIA = 'GEORGIA',
  GERMANY = 'GERMANY',
  GHANA = 'GHANA',
  GREECE = 'GREECE',
  GRENADA = 'GRENADA',
  GUATEMALA = 'GUATEMALA',
  GUINEA = 'GUINEA',
  GUINEA_BISSAU = 'GUINEA-BISSAU',
  GUYANA = 'GUYANA',
  HAITI = 'HAITI',
  HOLY_ROMAN_EMPIRE = 'HOLY_ROMAN_EMPIRE',
  HONDURAS = 'HONDURAS',
  HUNGARY = 'HUNGARY',
  ICELAND = 'ICELAND',
  INDIA = 'INDIA',
  INDONESIA = 'INDONESIA',
  IRAN = 'IRAN',
  IRAQ = 'IRAQ',
  REPUBLIC_OF_IRELAND = 'REPUBLIC_OF_IRELAND',
  ISRAEL = 'ISRAEL',
  ITALY = 'ITALY',
  IVORY_COAST = 'IVORY_COAST',
  JAMAICA = 'JAMAICA',
  JAPAN = 'JAPAN',
  JONATHANLAND = 'JONATHANLAND',
  JORDAN = 'JORDAN',
  KAZAKHSTAN = 'KAZAKHSTAN',
  KENYA = 'KENYA',
  KIRIBATI = 'KIRIBATI',
  NORTH_KOREA = 'NORTH_KOREA',
  SOUTH_KOREA = 'SOUTH_KOREA',
  KOSOVO = 'KOSOVO',
  KUWAIT = 'KUWAIT',
  KYRGYZSTAN = 'KYRGYZSTAN',
  LAOS = 'LAOS',
  LATVIA = 'LATVIA',
  LEBANON = 'LEBANON',
  LESOTHO = 'LESOTHO',
  LIBERIA = 'LIBERIA',
  LIBYA = 'LIBYA',
  LIECHTENSTEIN = 'LIECHTENSTEIN',
  LITHUANIA = 'LITHUANIA',
  LUXEMBOURG = 'LUXEMBOURG',
  MACEDONIA = 'MACEDONIA',
  MADAGASCAR = 'MADAGASCAR',
  MALAWI = 'MALAWI',
  MALAYSIA = 'MALAYSIA',
  MALDIVES = 'MALDIVES',
  MALI = 'MALI',
  MALTA = 'MALTA',
  MARSHALL_ISLANDS = 'MARSHALL_ISLANDS',
  MAURITANIA = 'MAURITANIA',
  MAURITIUS = 'MAURITIUS',
  MEXICO = 'MEXICO',
  MICRONESIA = 'MICRONESIA',
  MOLDOVA = 'MOLDOVA',
  MONACO = 'MONACO',
  MONGOLIA = 'MONGOLIA',
  MONTENEGRO = 'MONTENEGRO',
  MOROCCO = 'MOROCCO',
  MOUNT_ATHOS = 'MOUNT_ATHOS',
  MOZAMBIQUE = 'MOZAMBIQUE',
  NAMIBIA = 'NAMIBIA',
  NAURU = 'NAURU',
  NEPAL = 'NEPAL',
  NEWFOUNDLAND = 'NEWFOUNDLAND',
  NETHERLANDS = 'NETHERLANDS',
  NEW_ZEALAND = 'NEW_ZEALAND',
  NICARAGUA = 'NICARAGUA',
  NIGER = 'NIGER',
  NIGERIA = 'NIGERIA',
  NORWAY = 'NORWAY',
  OMAN = 'OMAN',
  OTTOMAN_EMPIRE = 'OTTOMAN_EMPIRE',
  PAKISTAN = 'PAKISTAN',
  PALAU = 'PALAU',
  PANAMA = 'PANAMA',
  PAPUA_NEW_GUINEA = 'PAPUA_NEW_GUINEA',
  PARAGUAY = 'PARAGUAY',
  PERU = 'PERU',
  PHILIPPINES = 'PHILIPPINES',
  POLAND = 'POLAND',
  PORTUGAL = 'PORTUGAL',
  PRUSSIA = 'PRUSSIA',
  QATAR = 'QATAR',
  ROMANIA = 'ROMANIA',
  ROME = 'ROME',
  RUSSIAN_FEDERATION = 'RUSSIAN_FEDERATION',
  RWANDA = 'RWANDA',
  GRENADINES = 'GRENADINES',
  SAMOA = 'SAMOA',
  SAN_MARINO = 'SAN_MARINO',
  SAO_TOME_PRINCIPE = 'SAO_TOME_&_PRINCIPE',
  SAUDI_ARABIA = 'SAUDI_ARABIA',
  SENEGAL = 'SENEGAL',
  SERBIA = 'SERBIA',
  SEYCHELLES = 'SEYCHELLES',
  SIERRA_LEONE = 'SIERRA_LEONE',
  SINGAPORE = 'SINGAPORE',
  SLOVAKIA = 'SLOVAKIA',
  SLOVENIA = 'SLOVENIA',
  SOLOMON_ISLANDS = 'SOLOMON_ISLANDS',
  SOMALIA = 'SOMALIA',
  SOUTH_AFRICA = 'SOUTH_AFRICA',
  SPAIN = 'SPAIN',
  SRI_LANKA = 'SRI_LANKA',
  SUDAN = 'SUDAN',
  SURINAME = 'SURINAME',
  SWAZILAND = 'SWAZILAND',
  SWEDEN = 'SWEDEN',
  SWITZERLAND = 'SWITZERLAND',
  SYRIA = 'SYRIA',
  TAJIKISTAN = 'TAJIKISTAN',
  TANZANIA = 'TANZANIA',
  THAILAND = 'THAILAND',
  TOGO = 'TOGO',
  TONGA = 'TONGA',
  TRINIDAD_TOBAGO = 'TRINIDAD_&_TOBAGO',
  TUNISIA = 'TUNISIA',
  TURKEY = 'TURKEY',
  TURKMENISTAN = 'TURKMENISTAN',
  TUVALU = 'TUVALU',
  UGANDA = 'UGANDA',
  UKRAINE = 'UKRAINE',
  UNITED_ARAB_EMIRATES = 'UNITED_ARAB_EMIRATES',
  UNITED_KINGDOM = 'UNITED_KINGDOM',
  URUGUAY = 'URUGUAY',
  UZBEKISTAN = 'UZBEKISTAN',
  VANUATU = 'VANUATU',
  VATICAN_CITY = 'VATICAN_CITY',
  VENEZUELA = 'VENEZUELA',
  VIETNAM = 'VIETNAM',
  YEMEN = 'YEMEN',
  ZAMBIA = 'ZAMBIA',
  ZIMBABWE = 'ZIMBABWE',
}

@Component({
  selector: 'app-register-as-refugee',
  templateUrl: './register-as-refugee.component.html',
  styleUrls: ['./register-as-refugee.component.css'],
})
export class RegisterAsRefugeeComponent implements OnInit {
  submissionStatus: 'success' | 'error' | null = null;

  languages = Object.values(Language);
  countries = Object.values(Country);
  refugeeForm!: FormGroup;
  isRefugeeNumberValid: boolean = true;

  constructor(private fb: FormBuilder, private apiService: ApiService, private router: Router) {}

  ngOnInit() {
    this.refugeeForm = this.fb.group({
      id: [0],
      name: ['RefugeeName'],
      emailAddress: ['RefugeeName@gmail.com'],
      phoneNumber: ['123'],
      country: ['USA'],
      gender: ['MALE'],
      userName: [
        '123',
        [
          Validators.required,
          Validators.minLength(6),
          Validators.maxLength(12),
        ],
      ],
      password: [
        '',
        [
          Validators.required,
          Validators.minLength(8),
          Validators.maxLength(50),
          Validators.pattern(/[A-Z]/),
        ],
      ],

      nationality: ['USA'],
      primaryLanguage: ['ENGLISH', Validators.required], // Set as required
      secondaryLanguage: ['ENGLISH'],
      citizenCard: [null, [Validators.required, Validators.pattern(/^\d{9}$/)]], // Ensure 9-digit number
      refugeeNumber: ['111111'],
      status: ['AWAITING'],
      birthdayDate: ['2024-03-05T09:46:45.621Z'],
    });
  }

  onSubmit() {
    // Check form validity
    if (this.refugeeForm.valid) {
      const refugeeNumber = this.refugeeForm.get('refugeeNumber')?.value || '';

      // First, check if the refugee number is valid
      this.apiService.checkRefugeeNumberExists(refugeeNumber).subscribe(
        (isValid) => {
          if (isValid) {
            this.apiService.sendFormData(this.refugeeForm.value).subscribe(
              (response) => {
                console.log('Form submission successful', response);
                this.submissionStatus = 'success'; // Update submission status
                // Handle successful form submission here
                this.router.navigate(['']);
              },
              (error) => {
                console.error('Form submission error', error);
                this.submissionStatus = 'error'; // Update submission status
                // Handle form submission error here
              }
            );
          } else {
            // Set the flag to false to show the error message
            this.isRefugeeNumberValid = false;
          }
        },
        (error) => {
          console.error('Error checking refugee number', error);
        }
      );
    } else {
      console.log('Form is not valid');
    }
  }
}
