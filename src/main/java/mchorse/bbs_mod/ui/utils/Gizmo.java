package mchorse.bbs_mod.ui.utils;

import mchorse.bbs_mod.ui.framework.elements.input.UIPropTransform;

public class Gizmo
{
    public final static Gizmo INSTANCE = null;

    public void register(int index, IGizmoHandler handler)
    {
    }

    public interface IGizmoHandler
    {
        public void start(Gizmo gizmo, int index, int mouseX, int mouseY, UIPropTransform transform);
    }
}
