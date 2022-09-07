import {Component} from '@angular/core';
import {AuthenticationService} from "./security/authentication.service";
import {Router} from "@angular/router";
import {TranslateService} from "@ngx-translate/core";
import {LanguageEnum} from "./LanguageEnum";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Hardware App';
  // @ts-ignore
  currentLanguage: string;

  constructor(
    public authenticationService: AuthenticationService,
    private router: Router,
    private translate : TranslateService
  ) {
    translate.setDefaultLang('hr');
    translate.use('hr')
  }

  ngOnInit(): void{
    this.setCurrentLanguageDropdownValue();
  }

  setCurrentLanguageDropdownValue() {
    if (this.translate.currentLang === LanguageEnum.HR) {
      this.translate.get('language.croatian').subscribe(language => this.currentLanguage = language);
    } else if (this.translate.currentLang === LanguageEnum.EN) {
      this.translate.get('language.english').subscribe(language => this.currentLanguage = language);
    }  else if (this.translate.currentLang === LanguageEnum.ES) {
      this.translate.get('language.spanish').subscribe(language => this.currentLanguage = language);
    }else {
      throw Error('Unknown current language!');
    }
  }

  onLanguageChange(newLanguage: string) {
    this.translate.use(newLanguage).subscribe(
      languageSwitched => this.setCurrentLanguageDropdownValue()
    );
  }

  logout() {
    this.authenticationService.logout();
    this.router.navigate(['/login']).then();
  }

}
