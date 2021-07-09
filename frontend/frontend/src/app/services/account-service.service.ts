import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Account } from '../models/Account';
import { Value } from '../models/Value';

@Injectable({
  providedIn: 'root'
})
export class AccountServiceService {
  private apiServerUrl = environment.apiBaseUrl;
  headers = new HttpHeaders().set('content-type','appliccation/json');

  constructor(private http: HttpClient) { }

  createAccount(account: Account): Observable<Account>{
    return this.http.post<Account>(this.apiServerUrl + '/account/add', account);
  }

  consign(number: String, value: Value){
    return this.http.put<any>(this.apiServerUrl + '/account/consign/' + number, value);

  }

  retire(number: String, value: Value){
    return this.http.put<any>(this.apiServerUrl + '/account/retire/' + number, value);
  }

  getValue(number: String){
    return this.http.get(this.apiServerUrl + '/account/' + number);
  }



}
