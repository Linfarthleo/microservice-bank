import { Component, OnInit } from '@angular/core'; 
import { ReporteService } from '../../../services/reporte.service';
import { Cliente } from '../../../models/cliente.model';
import { ClienteService } from '../../../services/cliente.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
 

@Component({ 

  selector: 'app-generar-reporte', 
standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './generar-reporte.component.html', 

  styleUrls: ['./generar-reporte.component.scss'], 

}) 

export class GenerarReporteComponent implements OnInit { 

  clientes: Cliente[] = []; 

  clienteSeleccionado: number | null = null; 

  fechaInicio: string = ''; 

  fechaFin: string = ''; 

 

  constructor( 

    private reporteService: ReporteService, 

    private clienteService: ClienteService 

  ) {} 

 

  ngOnInit(): void { 

    this.cargarClientes(); 

  } 

 

  cargarClientes(): void { 

    this.clienteService.obtenerClientes().subscribe((data) => { 

      this.clientes = data; 

    }); 

  } 

 

  formularioValido(): boolean { 

    return ( 

      this.clienteSeleccionado !== null && 

      this.fechaInicio !== '' && 

      this.fechaFin !== '' 

    ); 

  } 

 

  generarReporte(): void { 

    if (this.formularioValido()) { 

      this.reporteService 

        .generarReportePDF(this.clienteSeleccionado!, this.fechaInicio, this.fechaFin) 

        .subscribe((blob) => { 

          const url = window.URL.createObjectURL(blob); 

          const a = document.createElement('a'); 

          a.href = url; 

          a.download = `reporte_${this.clienteSeleccionado}_${this.fechaInicio}_${this.fechaFin}.pdf`; 

          a.click(); 

          window.URL.revokeObjectURL(url); 

        }); 

    } else { 

      alert('Por favor, complete todos los campos del formulario.'); 

    } 

  } 

} 