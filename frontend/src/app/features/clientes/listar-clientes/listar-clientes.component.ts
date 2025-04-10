import { Component, OnInit } from '@angular/core';
import { ClienteService } from '../../../services/cliente.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Cliente } from '../../../models/cliente.model';
import { Router } from '@angular/router';


@Component({
  selector: 'app-listar-clientes',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './listar-clientes.component.html',
  styleUrl: './listar-clientes.component.scss' 
})
export class ListarClientesComponent implements OnInit{
  clientes: Cliente[] = [];
  filtro = '';

  constructor(
    private clienteService: ClienteService,
    private router: Router

  ) { }
  
  ngOnInit(): void{
    this.obtenerClientes();
  }
  
  irACrearCliente(): void{
    this.router.navigate(['/clientes/crear']);
  }

  obtenerClientes(): void {
    this.clienteService.obtenerClientes().subscribe(
      (data) => { this.clientes = data; },
      (error) => {
        console.error('Error al obtener clientes', error)
      }
    );
  }
  

  filtrarClientes(): Cliente[]{
    return this.clientes.filter((cliente) =>
      cliente.nombre.toLowerCase().includes(this.filtro.toLowerCase()) ||
      cliente.identificacion.includes(this.filtro)
    );
  }
}
