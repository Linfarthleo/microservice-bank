import { Routes } from '@angular/router';
import { ListarClientesComponent } from './features/clientes/listar-clientes/listar-clientes.component';
import { CrearClienteComponent } from './features/clientes/crear-cliente/crear-cliente.component';
import { ListarMovimientosComponent } from './features/movimientos/listar-movimientos/listar-movimientos.component';
import { CrearMovimientoComponent } from './features/movimientos/crear-movimiento/crear-movimiento.component';
import { ListarCuentasComponent } from './features/cuentas/listar-cuentas/listar-cuentas.component';
import { CrearCuentaComponent } from './features/cuentas/crear-cuenta/crear-cuenta.component';
import { GenerarReporteComponent } from './features/reportes/generar-reporte/generar-reporte.component';

export const routes: Routes = [
    { path: 'clientes', component: ListarClientesComponent },
    { path: 'clientes/crear', component: CrearClienteComponent },
    { path: 'movimientos', component: ListarMovimientosComponent },
    { path: 'movimientos/crear', component: CrearMovimientoComponent },
    { path: 'cuentas', component: ListarCuentasComponent },
    { path: 'cuentas/crear', component: CrearCuentaComponent },
    { path: 'reportes', component: GenerarReporteComponent },
];