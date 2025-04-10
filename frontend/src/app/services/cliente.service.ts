import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Cliente } from '../models/cliente.model';

@Injectable({
  providedIn: 'root',
})
  
export class ClienteService {
  private baseUrl = 'http://localhost:8080/api/clientes';

  constructor(private http: HttpClient) { }



  obtenerClientes(): Observable<Cliente[]> {
    return this.http.get<Cliente[]>(this.baseUrl);
  }

  crearCliente(cliente: Cliente): Observable<Cliente> {
      return this.http.post<Cliente>(this.baseUrl, cliente);
  }

  verificarUsername(username: string): Observable<boolean>{
    return this.http.get<boolean>(`${this.baseUrl}/existe-username/${username}`)
  }
  
  obtenerClientePorId(id: number): Observable<Cliente> {
    return this.http.get<Cliente>(`${this.baseUrl}/${id}`);
  }

  actualizarCliente(id: number, cliente: Cliente): Observable<Cliente> {
    return this.http.put<Cliente>(`${this.baseUrl}/${id}`, cliente);
  }

  eliminarCliente(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }

}
