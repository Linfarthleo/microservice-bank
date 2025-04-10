import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Cuenta } from '../models/cuenta.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CuentaService {
  
  private baseUrl = 'http://localhost:8080/api/cuentas';

  constructor(private http: HttpClient) { }

  obtenerCuentas(): Observable<Cuenta[]> {
    return this.http.get<Cuenta[]>(this.baseUrl);
  }

  crearCuenta(cuenta: Cuenta): Observable<Cuenta> {
      return this.http.post<Cuenta>(this.baseUrl, cuenta);
  }
}
