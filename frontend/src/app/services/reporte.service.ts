import { Injectable } from '@angular/core'; 

import { HttpClient, HttpParams } from '@angular/common/http'; 

import { Observable } from 'rxjs'; 

 

@Injectable({ 

  providedIn: 'root', 

}) 

export class ReporteService { 

  private apiUrl = 'http://localhost:8080/api/reportes'; // Ajusta la URL según tu configuración 

 

  constructor(private http: HttpClient) {} 

 

  generarReportePDF(clienteId: number, inicio: string, fin: string): Observable<Blob> { 

    const params = new HttpParams() 

      .set('clienteId', clienteId.toString()) 

      .set('inicio', inicio) 

      .set('fin', fin); 

 

    return this.http.get(`${this.apiUrl}/pdf`, { 

      params, 

      responseType: 'blob', 

    }); 

  } 

} 