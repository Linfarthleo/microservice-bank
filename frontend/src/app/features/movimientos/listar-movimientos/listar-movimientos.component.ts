import { Component, OnInit } from '@angular/core';
import { Movimiento } from '../../../models/movimiento.model';
import { MovimientoService } from '../../../services/movimiento.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-listar-movimientos',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './listar-movimientos.component.html',
  styleUrl: './listar-movimientos.component.scss'
})
  
export class ListarMovimientosComponent implements OnInit{

  movimientos: Movimiento[] = [];
  filtro = '';

  constructor(
    private movimientoService: MovimientoService,
    private router: Router
  ) { }
  
  ngOnInit(): void{
    this.obtenerMovimientos();
  }

  irACrearMovimiento(): void{
    this.router.navigate(['/movimientos/crear']);
  }

  obtenerMovimientos(): void {
    this.movimientoService.obtenerMovimientos().subscribe(
      (data) => { this.movimientos = data; },
      (error) => {
        console.error('Error al obtener movimientos', error)
      }
    );
  }
  

  filtrarMovimientos(): Movimiento[]{
    return this.movimientos.filter((movimiento) =>
      movimiento.cuenta?.cliente?.nombre.toLowerCase().includes(this.filtro.toLowerCase())
    );
  }
}