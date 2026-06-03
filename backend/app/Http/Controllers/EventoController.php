<?php

namespace App\Http\Controllers;

use App\Models\Evento;
use Illuminate\Http\Request;

class EventoController extends Controller
{
    /**
     * Display a listing of the resource.
     */
    public function index()
    {
        return Evento::all();
    }

    /**
     * Store a newly created resource in storage.
     */
    public function store(Request $request)
    {
        $validated = $request->validate([
            'nombre' => ['required', 'string', 'max:255'],
            'fecha_hora' => ['required', 'string', 'max:255'],
            'lugar' => ['required', 'string', 'max:255'],
            'representante' => ['required', 'string', 'max:255'],
            'category' => ['required', 'string', 'max:255'],
        ]);

        $evento = Evento::create($validated);

        return response()->json($evento, 201);
    }

    /**
     * Display the specified resource.
     */
    public function show(string $id)
    {
        $evento = Evento::findOrFail($id);

        return response()->json($evento);
    }

    /**
     * Update the specified resource in storage.
     */
    public function update(Request $request, string $id)
    {
        $evento = Evento::findOrFail($id);

        $validated = $request->validate([
            'nombre' => ['sometimes', 'required', 'string', 'max:255'],
            'fecha_hora' => ['sometimes', 'required', 'string', 'max:255'],
            'lugar' => ['sometimes', 'required', 'string', 'max:255'],
            'representante' => ['sometimes', 'required', 'string', 'max:255'],
            'category' => ['sometimes', 'required', 'string', 'max:255'],
        ]);

        $evento->update($validated);

        return response()->json($evento);
    }

    /**
     * Remove the specified resource from storage.
     */
    public function destroy(string $id)
    {
        $evento = Evento::findOrFail($id);
        $evento->delete();

        return response()->noContent();
    }
}