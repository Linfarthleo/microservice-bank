import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Cuenta } from '../../../models/cuenta.model';
import { CuentaService } from '../../../services/cuenta.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-listar-cuentas',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './listar-cuentas.component.html',
  styleUrl: './listar-cuentas.component.scss'
})

export class ListarCuentasComponent implements OnInit{
  cuentas: Cuenta[] = [];
  filtro = '';

  constructor(
    private cuentaService: CuentaService,
    private router: Router
  ) { }
  
  ngOnInit(): void{
    this.obtenerCuentas();
  }

  irACrearCuenta(): void{
    this.router.navigate(['/cuentas/crear']);
  }
  

  obtenerCuentas(): void {
    this.cuentaService.obtenerCuentas().subscribe(
      (data) => { this.cuentas = data; },
      (error) => {
        console.error('Error al obtener cuentas', error)
      }
    );
  }
  

  filtrarCuentas(): Cuenta[]{
    return this.cuentas.filter((cuenta) =>
      cuenta.cliente?.nombre.toLowerCase().includes(this.filtro.toLowerCase()) ||
      cuenta.numeroCuenta.includes(this.filtro)
    );
  }
}