import { Component, OnInit } from '@angular/core';
import { Account } from '../models/Account';
import { Value } from '../models/Value';
import { AccountServiceService } from '../services/account-service.service';

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.css']
})
export class AccountComponent implements OnInit {

  constructor(private accountService: AccountServiceService) { }

  ngOnInit(): void {
  }

  createAccount(name: String, value: String){
    let account: Account = new Account(name, +value);
    this.accountService.createAccount(account).subscribe((resp: any) => {
      alert("cuenta creada, nùmero de cuenta: " + resp.number);
    });
  }

  consign(number: String, valor: String){
    let value: Value =  new Value(+valor);
    this.accountService.consign(number, value).subscribe((resp: any) => {
      alert("saldo en cuenta " + number + ": " + resp.value);
    });

  }

  retire(number: String, valor: String){
    let value: Value =  new Value(+valor);
    this.accountService.retire(number, value).subscribe((resp: any) => {
      alert("saldo en cuenta " + number + ": " + resp.value);
    });

  }

  getAccount(number: String){
    this.accountService.getValue(number).subscribe((resp: any) => {
      alert("saldo en cuenta " + number + ": " + resp.value);
    });
  }

}
