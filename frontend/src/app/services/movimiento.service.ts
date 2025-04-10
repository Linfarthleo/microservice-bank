import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Movimiento } from '../models/movimiento.model';

@Injectable({
  providedIn: 'root'
})
export class MovimientoService {

  private baseUrl = 'http://localhost:8080/api/movimientos';

  constructor(private http: HttpClient) { }

  obtenerMovimientos(): Observable<Movimiento[]> {
    return this.http.get<Movimiento[]>(this.baseUrl);
  }

  crearMovimiento(movimiento: Movimiento): Observable<Movimiento> {
      return this.http.post<Movimiento>(this.baseUrl, movimiento);
  }
}