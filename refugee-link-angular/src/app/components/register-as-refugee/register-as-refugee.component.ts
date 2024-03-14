import { Component } from '@angular/core';
import { OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ApiService } from '../../services/ApiService/api.service';

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
  USA = 'UNITED STATES OF AMERICA',
  AFGHANISTAN = 'AFGHANISTAN',
  ALBANIA = 'ALBANIA',
  ALGERIA = 'ALGERIA',
  ANDORRA = 'ANDORRA',
  ANGOLA = 'ANGOLA',
  ANTIGUA_DEPS = 'ANTIGUA & DEPS',
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
  BOSNIA_HERZEGOVINA = 'BOSNIA HERZEGOVINA',
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
  CAPE_VERDE = 'CAPE VERDE',
  CENTRAL_AFRICAN_REP = 'CENTRAL AFRICAN REP',
  CHAD = 'CHAD',
  CHILE = 'CHILE',
  CHINA = 'REPUBLIC OF CHINA',
  REPUBLIC_OF_CHINA = 'REPUBLIC OF CHINA',
  COLOMBIA = 'COLOMBIA',
  COMOROS = 'COMOROS',
  DEMOCRATIC_REPUBLIC_OF_THE_CONGO = 'DEMOCRATIC REPUBLIC OF THE CONGO',
  REPUBLIC_OF_THE_CONGO = 'REPUBLIC OF THE CONGO',
  COSTA_RICA = 'COSTA RICA',
  CROATIA = 'CROATIA',
  CUBA = 'CUBA',
  CYPRUS = 'CYPRUS',
  CZECH_REPUBLIC = 'CZECH REPUBLIC',
  DANZIG = 'DANZIG',
  DENMARK = 'DENMARK',
  DJIBOUTI = 'DJIBOUTI',
  DOMINICA = 'DOMINICA',
  DOMINICAN_REPUBLIC = 'DOMINICAN REPUBLIC',
  EAST_TIMOR = 'EAST TIMOR',
  ECUADOR = 'ECUADOR',
  EGYPT = 'EGYPT',
  EL_SALVADOR = 'EL SALVADOR',
  EQUATORIAL_GUINEA = 'EQUATORIAL GUINEA',
  ERITREA = 'ERITREA',
  ESTONIA = 'ESTONIA',
  ETHIOPIA = 'ETHIOPIA',
  FIJI = 'FIJI',
  FINLAND = 'FINLAND',
  FRANCE = 'FRANCE',
  GABON = 'GABON',
  GAZA_STRIP = 'GAZA STRIP',
  THE_GAMBIA = 'THE GAMBIA',
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
  HOLY_ROMAN_EMPIRE = 'HOLY ROMAN EMPIRE',
  HONDURAS = 'HONDURAS',
  HUNGARY = 'HUNGARY',
  ICELAND = 'ICELAND',
  INDIA = 'INDIA',
  INDONESIA = 'INDONESIA',
  IRAN = 'IRAN',
  IRAQ = 'IRAQ',
  REPUBLIC_OF_IRELAND = 'REPUBLIC OF IRELAND',
  ISRAEL = 'ISRAEL',
  ITALY = 'ITALY',
  IVORY_COAST = 'IVORY COAST',
  JAMAICA = 'JAMAICA',
  JAPAN = 'JAPAN',
  JONATHANLAND = 'JONATHANLAND',
  JORDAN = 'JORDAN',
  KAZAKHSTAN = 'KAZAKHSTAN',
  KENYA = 'KENYA',
  KIRIBATI = 'KIRIBATI',
  NORTH_KOREA = 'NORTH KOREA',
  SOUTH_KOREA = 'SOUTH KOREA',
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
  MARSHALL_ISLANDS = 'MARSHALL ISLANDS',
  MAURITANIA = 'MAURITANIA',
  MAURITIUS = 'MAURITIUS',
  MEXICO = 'MEXICO',
  MICRONESIA = 'MICRONESIA',
  MOLDOVA = 'MOLDOVA',
  MONACO = 'MONACO',
  MONGOLIA = 'MONGOLIA',
  MONTENEGRO = 'MONTENEGRO',
  MOROCCO = 'MOROCCO',
  MOUNT_ATHOS = 'MOUNT ATHOS',
  MOZAMBIQUE = 'MOZAMBIQUE',
  NAMIBIA = 'NAMIBIA',
  NAURU = 'NAURU',
  NEPAL = 'NEPAL',
  NEWFOUNDLAND = 'NEWFOUNDLAND',
  NETHERLANDS = 'NETHERLANDS',
  NEW_ZEALAND = 'NEW ZEALAND',
  NICARAGUA = 'NICARAGUA',
  NIGER = 'NIGER',
  NIGERIA = 'NIGERIA',
  NORWAY = 'NORWAY',
  OMAN = 'OMAN',
  OTTOMAN_EMPIRE = 'OTTOMAN EMPIRE',
  PAKISTAN = 'PAKISTAN',
  PALAU = 'PALAU',
  PANAMA = 'PANAMA',
  PAPUA_NEW_GUINEA = 'PAPUA NEW GUINEA',
  PARAGUAY = 'PARAGUAY',
  PERU = 'PERU',
  PHILIPPINES = 'PHILIPPINES',
  POLAND = 'POLAND',
  PORTUGAL = 'PORTUGAL',
  PRUSSIA = 'PRUSSIA',
  QATAR = 'QATAR',
  ROMANIA = 'ROMANIA',
  ROME = 'ROME',
  RUSSIAN_FEDERATION = 'RUSSIAN FEDERATION',
  RWANDA = 'RWANDA',
  GRENADINES = 'GRENADINES',
  SAMOA = 'SAMOA',
  SAN_MARINO = 'SAN MARINO',
  SAO_TOME_PRINCIPE = 'SAO TOME & PRINCIPE',
  SAUDI_ARABIA = 'SAUDI ARABIA',
  SENEGAL = 'SENEGAL',
  SERBIA = 'SERBIA',
  SEYCHELLES = 'SEYCHELLES',
  SIERRA_LEONE = 'SIERRA LEONE',
  SINGAPORE = 'SINGAPORE',
  SLOVAKIA = 'SLOVAKIA',
  SLOVENIA = 'SLOVENIA',
  SOLOMON_ISLANDS = 'SOLOMON ISLANDS',
  SOMALIA = 'SOMALIA',
  SOUTH_AFRICA = 'SOUTH AFRICA',
  SPAIN = 'SPAIN',
  SRI_LANKA = 'SRI LANKA',
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
  TRINIDAD_TOBAGO = 'TRINIDAD & TOBAGO',
  TUNISIA = 'TUNISIA',
  TURKEY = 'TURKEY',
  TURKMENISTAN = 'TURKMENISTAN',
  TUVALU = 'TUVALU',
  UGANDA = 'UGANDA',
  UKRAINE = 'UKRAINE',
  UNITED_ARAB_EMIRATES = 'UNITED ARAB EMIRATES',
  UNITED_KINGDOM = 'UNITED KINGDOM',
  URUGUAY = 'URUGUAY',
  UZBEKISTAN = 'UZBEKISTAN',
  VANUATU = 'VANUATU',
  VATICAN_CITY = 'VATICAN CITY',
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
  languages = Object.values(Language);
  countries = Object.values(Country);
  refugeeForm!: FormGroup;
  isRefugeeNumberValid: boolean = true;

  constructor(private fb: FormBuilder, private apiService: ApiService) {}

  ngOnInit() {
    this.refugeeForm = this.fb.group({
      id: [0],
      name: ['RefugeeName'],
      emailAddress: ['RefugeeName@gmail.com'],
      phoneNumber: ['123'],
      country: ['USA'],
      userName: ['123'],
      password: ['123'],
      nationality: ['USA'],
      primaryLanguage: ['ENGLISH', Validators.required], // Set as required
      secondaryLanguage: ['ENGLISH'],
      citizenCard: [123],
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
                // Handle successful form submission here
              },
              (error) => {
                console.error('Form submission error', error);
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
