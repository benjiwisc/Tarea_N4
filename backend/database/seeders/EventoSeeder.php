<?php

namespace Database\Seeders;

use App\Models\Evento;
use Illuminate\Database\Console\Seeds\WithoutModelEvents;
use Illuminate\Database\Seeder;

class EventoSeeder extends Seeder
{
    /**
     * Run the database seeds.
     */
    public function run(): void
    {
        Evento::create([
            'nombre' => 'Concierto de apertura',
            'fecha_hora' => '2026-06-10 20:00',
            'lugar' => 'Auditorio Central',
            'representante' => 'María López',
            'category' => 'Conciertos',
        ]);

        Evento::create([
            'nombre' => 'Charla de innovación',
            'fecha_hora' => '2026-06-12 18:30',
            'lugar' => 'Sala Principal',
            'representante' => 'Luis Torres',
            'category' => 'Conferencias',
        ]);

        Evento::create([
            'nombre' => 'Taller práctico de apps',
            'fecha_hora' => '2026-06-15 09:00',
            'lugar' => 'Laboratorio 2',
            'representante' => 'Ana Méndez',
            'category' => 'Talleres',
        ]);
    }
}
